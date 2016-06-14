package xb.manager;

import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.DocumentDescriptor;
import com.marklogic.client.document.XMLDocumentManager;

import xb.conversion.JaxbXMLConverter;
import xb.database.DatabaseConnection;

/**
 * Ovde se pozivaju metode iz DatabaseManager-a za izvrsavanje operacija nad objektima u bazi.
 * @author Ivana
 *
 */
public class ObjectManager<T> {
	
	private DatabaseClient client;
	private DatabaseManager<T> dbManager;
	private XMLDocumentManager xmlDocManager;
	private JaxbXMLConverter<T> converter;
	private SchemaFactory schemaFactory;
	private Schema schema;
	
	public ObjectManager() {}
	
	public ObjectManager(URL urlSchema) {
		
		client = DatabaseConnection.getDbClient();
		xmlDocManager = client.newXMLDocumentManager();
		converter = new JaxbXMLConverter<>();
		schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			schema = schemaFactory.newSchema(urlSchema);
		} catch (SAXException e) {
			e.printStackTrace();
		}
		dbManager = new DatabaseManager<>(client, xmlDocManager, converter, schemaFactory, schema);
	}
	

	/**
	 * Upis XML fajla u bazu sa prosledjenim id-em dokumenta.
	 * @param xmlPath
	 * @param docId
	 * @param collId
	 */
	public void writeXMLtoDB(String xmlPath, String docId, String collId) {
		dbManager.writeXMLtoDB(xmlPath, docId, collId);
	}
	
	/**
	 * Upis Jaxb objekta u bazu sa prosledjenim id-em dokumenta.
	 * @param object
	 * @param docId
	 * @param collId
	 * @param xmlPath
	 */
	public void writeObjectToDB(T object, String docId, String collId) {
		dbManager.writeObjectToDB(object, docId, collId);
	}
	
	public void writeXMLtoDB(String xmlPath, String collId) {
		dbManager.writeXMLtoDB(xmlPath, collId);
	}
	
	public void writeObjectToDB(T object, String collId) {
		dbManager.writeObjectToDB(object, collId);
	}
	
	public T readFromDB(String docId) {
		return dbManager.readFromDB(docId, schema);
	}
	
	public void deleteFromDB(String docId) {
		dbManager.deleteFromDB(docId);
	}

}
