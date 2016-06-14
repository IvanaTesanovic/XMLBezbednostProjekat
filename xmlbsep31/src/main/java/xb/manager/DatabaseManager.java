package xb.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.DocumentDescriptor;
import com.marklogic.client.document.DocumentUriTemplate;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DOMHandle;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.InputStreamHandle;

import xb.conversion.JaxbXMLConverter;
import xb.signing.SignEnveloped;

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
	
	/**
	 * Upisivanje Jaxb objekta u bazu podataka sa id-em dokumenta. Prethodi mu konverzija objekta u XML.
	 * @param object Jaxb objekat koji konvertujemo
	 * @param docId
	 * @param collId
	 * @param xmlOutputPath
	 */
	public void writeObjectToDB(T object, String docId, String collId) {
		String outputPath = "tem.xml";
		//nakon kreiranja temporary dokumenta, treba ga potpisati i tek onda poslati na server
		//SignEnveloped.sign(outputPath);
		try {
			//File file = new File(FirstController.class.getClassLoader().getResource("Output/tempXML.xml").toURI());
			if(converter.marshalling(outputPath, object))
				writeXMLtoDB(outputPath, docId, collId);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return desc;
	}
	
	/**
	 * Upisivanje Jaxb objekta u bazu podataka sa generisanim id-em dokumenta. Prethodi mu konverzija objekta u XML.
	 * @param object
	 * @param collId
	 */
	public void writeObjectToDB(T object, String collId) {
		String outputPath = "tem.xml";
		try {
			//File file = new File(FirstController.class.getClassLoader().getResource("Output/tempXML.xml").toURI());
			if(converter.marshalling(outputPath, object))
				writeXMLtoDB(outputPath, collId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Citanje iz baze podataka i prevod XML fajla u Jaxb objekat.
	 * @param docId
	 * @param xmlSchema
	 * @return
	 */
	public T readFromDB(String docId, Schema xmlSchema) {
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
	
	public void deleteFromDB(String docId) {
		xmlDocManager.delete(docId);
	}
	
}