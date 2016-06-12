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
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DOMHandle;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.InputStreamHandle;

import xb.conversion.JaxbXMLConverter;

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
	 * Upisivanje XML fajla u bazu podataka.
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
	 * Upisivanje Jaxb objekta u bazu podataka. Prethodi mu konverzija objekta u XML.
	 * @param object Jaxb objekat koji konvertujemo
	 * @param docId
	 * @param collId
	 * @param xmlOutputPath
	 */
	public void writeObjectToDB(T object, String docId, String collId){
		String outputPath = "tem.xml";
		try {
			//File file = new File(FirstController.class.getClassLoader().getResource("Output/tempXML.xml").toURI());
			if(converter.marshalling(outputPath, object))
				writeXMLtoDB(outputPath, docId, collId);
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
	
}