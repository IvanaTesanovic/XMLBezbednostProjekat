package xb.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.security.PrivateKey;
import java.security.cert.Certificate;

import javax.crypto.SecretKey;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.DocumentDescriptor;
import com.marklogic.client.document.DocumentPatchBuilder;
import com.marklogic.client.document.DocumentPatchBuilder.Position;
import com.marklogic.client.document.DocumentUriTemplate;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DOMHandle;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.InputStreamHandle;
import com.marklogic.client.io.SearchHandle;
import com.marklogic.client.io.marker.DocumentPatchHandle;
import com.marklogic.client.query.MatchDocumentSummary;
import com.marklogic.client.query.StringQueryDefinition;
import com.marklogic.client.util.EditableNamespaceContext;

import xb.certificate.CertificateGenerator;
import xb.certificate.KeyStoreWriter;
import xb.controller.api.DodajAmandmanController;
import xb.conversion.JaxbXMLConverter;
import xb.database.DatabaseConnection;
import xb.encryption.DecryptKEK;
import xb.encryption.EncryptKEK;
import xb.model.TipKorisnik;
import xb.signing.SignEnveloped;
import xb.signing.VerifySignatureEnveloped;
import xb.validation.ReflectionUtils;
import xb.validation.XMLValidator;

/**
 * Sadrzi operacije koje se obavljaju nad bazom podataka.
 * @author Ivana
 *
 */
public class DatabaseManager<T> {
	
	private DatabaseClient client;
	private XMLDocumentManager xmlDocManager;
	private JaxbXMLConverter<T> converter;
	private SchemaFactory schemaFactory;
	private Schema schema;
	
	private static TransformerFactory transformerFactory;
	
	static {
		transformerFactory = TransformerFactory.newInstance();
	}
	
	public DatabaseManager(DatabaseClient client, XMLDocumentManager xmlDocManager, JaxbXMLConverter<T> converter,
			SchemaFactory schemaFactory, Schema schema) {
		super();
		this.client = client;
		this.xmlDocManager = xmlDocManager;
		this.converter = converter;
		this.schemaFactory = schemaFactory;
		this.schema = schema;
	}

	/**
	 * Upisivanje XML fajla u bazu podataka sa id-em dokumenta.
	 * @param xmlPath
	 * @param docId
	 * @param collId
	 */
	public void writeXMLtoDB(String xmlPath, String docId, String collId) {
		try {
			InputStreamHandle handle = new InputStreamHandle(new FileInputStream(xmlPath));
			DocumentMetadataHandle metadata = new DocumentMetadataHandle();
			metadata.getCollections().add(collId);
			xmlDocManager.write(docId, metadata, handle);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void writeXMLtoDB(File file, String docId, String collId) {
		try {
			InputStreamHandle handle = new InputStreamHandle(new FileInputStream(file));
			DocumentMetadataHandle metadata = new DocumentMetadataHandle();
			metadata.getCollections().add(collId);
			xmlDocManager.write(docId, metadata, handle);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Upisivanje Jaxb objekta u bazu podataka sa id-em dokumenta. Prethodi mu konverzija objekta u XML.
	 * @param object Jaxb objekat koji konvertujemo
	 * @param docId
	 * @param collId
	 * @param xmlOutputPath
	 */
	public void writeObjectToDB(T object, String docId, String collId, boolean toSign) {
		String outputPath = "tem.xml";
		try {
			if(converter.marshalling(outputPath, object)) {
				if(toSign) {
					if(signDocument(null))
						writeXMLtoDB(outputPath, docId, collId);
				}
				else
					writeXMLtoDB(outputPath, docId, collId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Upisivanje XML fajla u bazu podataka sa generisanim id-em dokumenta.
	 * @param path
	 * @param collId
	 */
	public DocumentDescriptor writeXMLtoDB(String path, String collId) {
		InputStreamHandle handle;
		DocumentDescriptor desc = null;
		try {
			handle = new InputStreamHandle(new FileInputStream(path));
			DocumentUriTemplate template = xmlDocManager.newDocumentUriTemplate("xml");
			DocumentMetadataHandle metadata = new DocumentMetadataHandle();
			metadata.getCollections().add(collId);
			desc =  xmlDocManager.create(template, metadata, handle);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return desc;
	}
	
	public DocumentDescriptor writeXMLtoDB(File file, String collId) {
		InputStreamHandle handle;
		DocumentDescriptor desc = null;
		try {
			handle = new InputStreamHandle(new FileInputStream(file));
			DocumentUriTemplate template = xmlDocManager.newDocumentUriTemplate("xml");
			DocumentMetadataHandle metadata = new DocumentMetadataHandle();
			metadata.getCollections().add(collId);
			desc =  xmlDocManager.create(template, metadata, handle);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return desc;
	}
	
	/**
	 * Upisivanje Jaxb objekta u bazu podataka sa generisanim id-em dokumenta. Prethodi mu konverzija objekta u XML.
	 * @param object
	 * @param collId
	 */
	public DocumentDescriptor writeObjectToDB(T object, String collId, boolean toSign) {
		String outputPath = "tem.xml";
		DocumentDescriptor desc = null;
		try {
			if(converter.marshalling(outputPath, object)) {
				if(toSign) {
					if(signDocument(null))
						return writeXMLtoDB(outputPath, collId);
				}
				else
					return writeXMLtoDB(outputPath, collId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return desc;
	}
	
	/**
	 * Citanje iz baze podataka i prevod XML fajla u Jaxb objekat.
	 * @param docId
	 * @param xmlSchema
	 * @return
	 */
	public T readObjectFromDB(String docId, Schema xmlSchema) {
		T object = null;
		File outputFile = new File("out.xml");
		DOMHandle content = new DOMHandle();
		DocumentMetadataHandle metadata = new DocumentMetadataHandle();
		xmlDocManager.read(docId, metadata, content);
		Document doc = content.get();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(outputFile);
			//transformer ce sada upisati direktno u taj fal, odn. "out.xml"
			transformer.transform(source, result);
			object = converter.unmarshalling("out.xml", xmlSchema);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
	/**
	 * Citanje fajlova iz baze koje prati provera potpisa.
	 * @param docId
	 * @param xmlSchema
	 * @return da li je dokument validno potpisan
	 */
	public boolean verifySignature(String docId) {
		boolean retVal = false;
		File outputFile = new File("out.xml");
		DOMHandle content = new DOMHandle();
		DocumentMetadataHandle metadata = new DocumentMetadataHandle();
		xmlDocManager.read(docId, metadata, content);
		Document doc = content.get();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(outputFile);
			//transformer ce sada upisati direktno u taj fal, odn. "out.xml"
			transformer.transform(source, result);
			//provera potpisa
			VerifySignatureEnveloped vse = new VerifySignatureEnveloped();
			if(vse.verifySignature(doc))
				retVal = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	/**
	 * Brisanje fajla iz baze za prosledjeni id.
	 * @param docId
	 */
	public void deleteFromDB(String docId) {
		xmlDocManager.delete(docId);
	}
	
	/**
	 * Metoda za potpisivanje XML fajlova.
	 * @param xmlPath
	 * @return
	 */
	public boolean signDocument(String xmlPath) {
		boolean retVal = false;
		Document document;
		SignEnveloped se = new SignEnveloped();
		
		try {
			if(xmlPath == null)
				document = se.loadDocument(new FileInputStream(new File("tem.xml")));
			else
				document = se.loadDocument(new FileInputStream(new File(xmlPath)));
			
			PrivateKey pk = se.readPrivateKey();
			Certificate cert = se.readCertificate();
			Document signedDoc = se.signDocument(document, pk, cert);
			se.saveDocument(signedDoc, "tem.xml");
			retVal = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	public Document encryptDocument(String xmlPath) {
		boolean retVal = false;
		Document document;
		Document encryptedDoc = null;
		EncryptKEK enc = new EncryptKEK();
		
		try {
			if(xmlPath == null)
				document = enc.loadDocument(new FileInputStream(new File("tem.xml")));
			else
				document = enc.loadDocument(new FileInputStream(new File(xmlPath)));
			
			SecretKey secretKey = enc.generateDataEncryptionKey();
	    	Certificate cert = enc.readCertificate();
	    	encryptedDoc = enc.encrypt(document, secretKey, cert);
	    	retVal = true;
			enc.saveDocument(encryptedDoc, "tem.xml");
			} catch (Exception e) {
				e.printStackTrace();
			}
		return encryptedDoc;
	}
	
	/**
	 * Metoda koja salje XML fajlove iz baze podataka Istorijskom Arhivu. Pre slanja svaki dokument se enkriptuje.
	 * @param docId
	 */
	public void sendToIAGNS(String docId) {
		File outputFile = new File("tem.xml");
		DOMHandle content = new DOMHandle();
		DocumentMetadataHandle metadata = new DocumentMetadataHandle();
		xmlDocManager.read(docId, metadata, content);
		Document doc = content.get();
		try {
			Document bla = encryptDocument(null); 
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(bla);
			StreamResult result = new StreamResult(outputFile);
			transformer.transform(source, result); //transformer upisuje u outputFile
			writeXMLtoDB(outputFile, DatabaseConnection.IAGNS_COL_ID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda koja obezbedjuje dekripciju dokumenta iz baze podataka.
	 * @param docId
	 * @return
	 */
	public boolean decryptDocument(String docId) {
		boolean retVal = false;
		DecryptKEK dec = new DecryptKEK();
		PrivateKey pk = dec.readPrivateKey();
		File outputFile = new File("out.xml");
		DOMHandle content = new DOMHandle();
		DocumentMetadataHandle metadata = new DocumentMetadataHandle();
		xmlDocManager.read(docId, metadata, content);
		Document doc = content.get();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(outputFile);
			//transformer ce sada upisati direktno u taj fal, odn. "out.xml"
			transformer.transform(source, result);
			//dekripcija
			if(dec.decrypt(doc, pk))
				retVal = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	public boolean validateAndSaveXMLAmandman(String xmlSource) {
		try {
			//Parse the given input
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlSource)));
			
			//Write the parsed document to an xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			File tempPredlog = new File("tempPredlog.xml");
			StreamResult result = new StreamResult(tempPredlog);
			transformer.transform(source, result);
			
			XMLValidator handler = new XMLValidator();
			if (handler.parse(tempPredlog, DodajAmandmanController.class.getClassLoader().getResource("Schemas/Amandman-novi.xsd"))) {
				writeXMLtoDB(tempPredlog, DatabaseConnection.AMD_COL_ID);
				return true;
			}	
			
		} catch (Exception e) {
			//TODO handle exception
		}
		return false;		
	}
	
	public boolean validateAndSaveXMLAkt(String xmlSource) {
		try {
			//Parse the given input
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlSource)));
			
			//Write the parsed document to an xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			File tempPredlog = new File("tempPredlog.xml");
			StreamResult result = new StreamResult(tempPredlog);
			transformer.transform(source, result);
			
			XMLValidator handler = new XMLValidator();
			if (handler.parse(tempPredlog, DodajAmandmanController.class.getClassLoader().getResource("Schemas/Akt.xsd"))) {
				writeXMLtoDB(tempPredlog, DatabaseConnection.AKT_COL_ID);
				return true;
			}
			
		} catch (Exception e) {
			//TODO handle exception
		}
		return false;
		
	}
	/**
	 * Metoda koja pretrazuje kolekciju za zadati parametar.
	 * @param param
	 * @param collId
	 * @return
	 */
	public MatchDocumentSummary[] searchColByParam(String param, String collId) {

        com.marklogic.client.query.QueryManager queryManager = client.newQueryManager();
        StringQueryDefinition queryDefinition = queryManager.newStringDefinition();
        queryDefinition.setCriteria(param);
        queryDefinition.setCollections(collId);
        SearchHandle results = queryManager.search(queryDefinition, new SearchHandle());
        MatchDocumentSummary matches[] = results.getMatchResults();
        return matches;
    }
	
//	public String partialUpdate(String docId, String patch, String operation) {
//
//		File file = new File("file.xml");
//        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//        
//		try {
//			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//	        Document doc = docBuilder.parse(file);
//			Node data = doc.getFirstChild();
//			 
//			Node startdate = doc.getElementsByTagName("Datum").item(0);
//            return startdate.getNodeValue();
//			 
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "ne valja";

		
//		// Defining namespace mappings
//		EditableNamespaceContext namespaces = new EditableNamespaceContext();
////		namespaces.put("b", "http://www.ftn.uns.ac.rs/xpath/examples");
////		namespaces.put("fn", "http://www.w3.org/2005/xpath-functions");
//		//namespaces.put("s", "http://www.skupstinans.rs");
//		// Assigning namespaces to patch builder
//		DocumentPatchBuilder patchBuilder = xmlDocManager.newPatchBuilder();
//		//patchBuilder.setNamespaces(namespaces);
//
//				// Creating an XML patch
//				/*
//						<b:book category="TEST">
//							<b:title lang=\"en\">Test book</b:title>
//							<b:author>Test Author</b:author>
//							<b:year>2016</b:year>
//							<b:price>59.99</b:price>
//						</b:book>
//				 */
//		
//		// Defining XPath context
//		//String contextXPath1 = "/b:bookstore/b:book[1]";
//		String contextXPath2 = "/Sadrzaj";
//		
//		if(operation.equals("Dodavanje")) {
//			//patchBuilder.insertFragment(contextXPath1, Position.BEFORE, patch);
//			//patchBuilder.insertFragment(contextXPath1, Position.AFTER, patch);
//			patchBuilder.insertFragment(contextXPath2, Position.BEFORE, patch);
//		}
//		else if(operation.equals("Brisanje")) {
//			//patchBuilder.delete(contextXPath1);
//		}
//		else if(operation.equals("Izmena")) {
//			//patchBuilder.replaceFragment(contextXPath1, patch);
//		}
//		
//		DocumentPatchHandle patchHandle = patchBuilder.build();
//		xmlDocManager.patch(docId, patchHandle);
		
	//}
	
	public String updateAkt(String docId, String putanja, String resenje, String sadrzaj) {
		String retVal = "ne valja";
		File outputFile = new File("out.xml");
		DOMHandle content = new DOMHandle();
		DocumentMetadataHandle metadata = new DocumentMetadataHandle();
		xmlDocManager.read(docId, metadata, content);
		Document doc = content.get();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(outputFile);
			//transformer ce sada upisati direktno u taj fal, odn. "out.xml"
			transformer.transform(source, result);
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder b = f.newDocumentBuilder();
			Document docFile = b.parse(outputFile);
			
			if(resenje.equals("Izmena")) {
				String[] odredbe = putanja.split("-");
				
				if(odredbe.length == 2) {
					NodeList docElem = docFile.getDocumentElement().getElementsByTagName("Stav");
					if(sadrzaj.startsWith("<"))
						docElem.item(Integer.parseInt(odredbe[1].substring(4))).setTextContent(sadrzaj);
					else
						docElem.item(Integer.parseInt(odredbe[1].substring(4))).setTextContent("<Tekst><Sadrzaj>" + sadrzaj + "</Sadrzaj></Tekst>");
				}
				else if(odredbe.length == 3) {
					NodeList docElem = docFile.getDocumentElement().getElementsByTagName("Tacka");
					if(sadrzaj.startsWith("<"))
						docElem.item(Integer.parseInt(odredbe[2].substring(5))).setTextContent(sadrzaj);
					else
						docElem.item(Integer.parseInt(odredbe[2].substring(5))).setTextContent("<Tekst><Sadrzaj>" + sadrzaj + "</Sadrzaj></Tekst>");
				}
				else if(odredbe.length == 4) {
					NodeList docElem = docFile.getDocumentElement().getElementsByTagName("Podtacka");
					if(sadrzaj.startsWith("<"))
						docElem.item(Integer.parseInt(odredbe[3].substring(8))).setTextContent(sadrzaj);
					else
						docElem.item(Integer.parseInt(odredbe[3].substring(8))).setTextContent("<Tekst><Sadrzaj>" + sadrzaj + "</Sadrzaj></Tekst>");
				}
				else if(odredbe.length == 5) {
					NodeList docElem = docFile.getDocumentElement().getElementsByTagName("Alineja");
					docElem.item(Integer.parseInt(odredbe[4].substring(7))).setTextContent("<Tekst><Sadrzaj>" + sadrzaj + "</Sadrzaj></Tekst>");
				}
			}
			else if(resenje.equals("Dodavanje")) {
				
				String[] odredbe = putanja.split("-");
				
				if(odredbe.length == 2) {
					NodeList docElem = docFile.getDocumentElement().getElementsByTagName("Stav");
					String name = docElem.item(0).getFirstChild().getNodeName();
					if(sadrzaj.startsWith("<"))
						docElem.item(Integer.parseInt(odredbe[1].substring(4))).setTextContent(sadrzaj);
					else
						docElem.item(Integer.parseInt(odredbe[1].substring(4))).setTextContent("<Tekst><Sadrzaj>" + sadrzaj + "</Sadrzaj></Tekst>");
				}
				else if(odredbe.length == 3) {
					NodeList docElem = docFile.getDocumentElement().getElementsByTagName("Tacka");
					if(sadrzaj.startsWith("<"))
						docElem.item(Integer.parseInt(odredbe[2].substring(5))).setTextContent(sadrzaj);
					else
						docElem.item(Integer.parseInt(odredbe[2].substring(5))).setTextContent("<Tekst><Sadrzaj>" + sadrzaj + "</Sadrzaj></Tekst>");
				}
				else if(odredbe.length == 4) {
					NodeList docElem = docFile.getDocumentElement().getElementsByTagName("Podtacka");
					if(sadrzaj.startsWith("<"))
						docElem.item(Integer.parseInt(odredbe[3].substring(8))).setTextContent(sadrzaj);
					else
						docElem.item(Integer.parseInt(odredbe[3].substring(8))).setTextContent("<Tekst><Sadrzaj>" + sadrzaj + "</Sadrzaj></Tekst>");
				}
				else if(odredbe.length == 5) {
					NodeList docElem = docFile.getDocumentElement().getElementsByTagName("Alineja");
					docElem.item(Integer.parseInt(odredbe[4].substring(7))).setTextContent("<Tekst><Sadrzaj>" + sadrzaj + "</Sadrzaj></Tekst>");
				}
				
			}
			else if(resenje.equals("Brisanje")) {
				
				String[] odredbe = putanja.split("-");
				
				if(odredbe.length == 2) {
					NodeList docElem = docFile.getDocumentElement().getElementsByTagName("Stav");
					docFile.removeChild(docElem.item(Integer.parseInt(odredbe[1].substring(4))));
				}
				else if(odredbe.length == 3) {
					NodeList docElem = docFile.getDocumentElement().getElementsByTagName("Tacka");
					docFile.removeChild(docElem.item(Integer.parseInt(odredbe[2].substring(5))));
				}
				else if(odredbe.length == 4) {
					NodeList docElem = docFile.getDocumentElement().getElementsByTagName("Podtacka");
					docFile.removeChild(docElem.item(Integer.parseInt(odredbe[3].substring(8))));
				}
				else if(odredbe.length == 5) {
					NodeList docElem = docFile.getDocumentElement().getElementsByTagName("Alineja");
					docFile.removeChild(docElem.item(Integer.parseInt(odredbe[4].substring(7))));
				}
				
			}
			
			File newFile = new File("newxml.xml");
		    // instance of a DocumentBuilderFactory
		    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		    DocumentBuilder db = dbf.newDocumentBuilder();
	        // create instance of DOM
		    Transformer tr = transformerFactory.newTransformer();
		    tr.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.transform(new DOMSource(docFile), new StreamResult(new FileOutputStream(newFile)));
			
			writeXMLtoDB(newFile, docId, DatabaseConnection.USV_AKT_COL_ID);
			retVal = "upisuje";
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	public void generateKeyStore(TipKorisnik korisnik) {
		KeyStoreWriter ksw = new KeyStoreWriter();
		ksw.generateKeyStore(korisnik);
	}
}
