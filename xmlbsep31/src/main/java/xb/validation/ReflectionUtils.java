package xb.validation;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import xb.controller.api.DodajAmandmanController;
import xb.manager.DatabaseManager;

public class ReflectionUtils {
	
	public static String getConstantName(int value, Class<?> c) throws IllegalArgumentException, IllegalAccessException {
		for (Field field : c.getDeclaredFields()) {
			if (field.getType() == int.class && field.getInt(null) == value) {
				return field.getName();
			}
		}
		return "";
	}
}
