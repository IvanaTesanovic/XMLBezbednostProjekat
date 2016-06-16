package xb.validation;

import static javax.xml.stream.XMLStreamConstants.ATTRIBUTE;
import static javax.xml.stream.XMLStreamConstants.CDATA;
import static javax.xml.stream.XMLStreamConstants.CHARACTERS;
import static javax.xml.stream.XMLStreamConstants.COMMENT;
import static javax.xml.stream.XMLStreamConstants.DTD;
import static javax.xml.stream.XMLStreamConstants.END_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.ENTITY_REFERENCE;
import static javax.xml.stream.XMLStreamConstants.PROCESSING_INSTRUCTION;
import static javax.xml.stream.XMLStreamConstants.SPACE;
import static javax.xml.stream.XMLStreamConstants.START_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;
import static xb.validation.ReflectionUtils.getConstantName;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

/**
* Validacija XML fajla u odnosu na njegovu XML shemu,
* upotrebom kursor API-ja StAX parsera.
*/

public class XMLValidator {
	
	private static XMLInputFactory inputFactory;

	private static XMLOutputFactory outputFactory;
	
	/*
	 * Factory initialization static-block
	 */
	static {
		inputFactory = XMLInputFactory.newInstance();
		inputFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
		
		outputFactory = XMLOutputFactory.newInstance();
		outputFactory.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, true);
	}
	
	/**
	 * Cursor API for parsing XML files.
	 * @param filePath Argument passed to main method (e.g. resources/Data/temp.xml)
	 * @param schemaPath 
	 */
	public void parse(File file, URL schemaURL) {

		try {
			XMLStreamReader streamReader = inputFactory.createXMLStreamReader(new FileInputStream(file));
		    
		    // Validate XML document against XML schema 
			// Embed <test/> element in XML document.
		    if (validates(file, schemaURL)) {
		    
		    	System.out.println("[INFO] XML document passes validation against XML schema.");
		    	
				while (streamReader.hasNext()) {
					handleEvent(streamReader);
					streamReader.next();
				}
		    }
		} catch (XMLStreamException e) {
		    e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Validates XML document against XML schema.
	 * 
	 * @param filePath file path.
	 * @param schemaPath XML schema path.
	 * @return validation indicator. 
	 * @throws XMLStreamException 
	 * @throws SAXException 
	 * @throws IOException 
	 */
	private boolean validates(File file, URL schemaURL) {
				
		XMLStreamReader streamReader;
		try {
			streamReader = inputFactory.createXMLStreamReader(new FileInputStream(file));
			
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		    Schema schema = factory.newSchema(schemaURL);

		    Validator validator = schema.newValidator();
		    validator.validate(new StAXSource(streamReader));
		    
		} catch (SAXException e) {
			System.out.println("[ERROR] XML file failed validation against schema.");
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private void handleEvent(XMLStreamReader streamReader) throws Exception {
		
		// Determine event type
		int eventType = streamReader.getEventType();

		// Do not print line number for whitespace characters
		if (!streamReader.isWhiteSpace())
			System.out.print("Line " + streamReader.getLocation().getLineNumber() + ", ");
		
	    switch (eventType) {
	        case START_ELEMENT:
	        	
	        	System.out.print(getConstantName(eventType, XMLStreamConstants.class) + ": ");
	        	System.out.print(streamReader.getLocalName());

	        	if (streamReader.getAttributeCount() > 0) {
	        		
	        		System.out.print(", ATTRIBUTES: ");
	        	
		        	for (int i=0; i<streamReader.getAttributeCount(); i++) {
		        		System.out.print(streamReader.getAttributeLocalName(i) + "=" + streamReader.getAttributeValue(i));
		        		if (i < streamReader.getAttributeCount()-1)
		        			System.out.print(", ");
		        	}
	        	}
	        	System.out.println();
	        	break;
	        	
	        case END_ELEMENT:
	        	System.out.print(getConstantName(eventType, XMLStreamConstants.class) + ": ");
	        	System.out.println(streamReader.getName().getLocalPart());
	        	break;
	        	
	        case CHARACTERS:
	        	if (!streamReader.isWhiteSpace())
	        		System.out.println(getConstantName(eventType,	XMLStreamConstants.class) + ": " + streamReader.getText());
	        	break;
	        	
	        case PROCESSING_INSTRUCTION:
	        case COMMENT:
	        case START_DOCUMENT:
	        case END_DOCUMENT:
	        case ENTITY_REFERENCE:
	        case ATTRIBUTE:
	        case DTD:
	        case CDATA:
	        case SPACE:
	            System.out.println(getConstantName(eventType, XMLStreamConstants.class));
	        	break;
	        default:
	        	System.out.println("UNKNOWN_EVENT_TYPE, " + eventType);
	    } 
	}
}
