package xb.conversion;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;

/**
 * Klasa za konverziju izmedju Jaxb objekata i XML fajlova.
 * @author Ivana
 *
 * @param <T> objekat koji ucestvuje u konverziji
 */
public class JaxbXMLConverter<T> {
	
	/**
	 * Konverzija iz XML fajla u Jaxb objekat.
	 * @param xmlPath putanja do XML fajla
	 * @param xmlSchema sema za dati XML fajl
	 * @return konvertovan Jaxb objekat
	 */
	@SuppressWarnings("unchecked")
	public T unmarshalling(String xmlPath, Schema xmlSchema) {
		T retVal = null;
		try {
		JAXBContext context = JAXBContext.newInstance("xb.model");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setSchema(xmlSchema);
		retVal =  (T) unmarshaller.unmarshal(new File(xmlPath));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return retVal;
	}
	

	/**
	 * Konverzija iz Jaxb objekta u XML fajl.
	 * @param xmlPath putanja i naziv XML fajla u koji ce se konvertovati
	 * @param object Jaxb objekat koji konvertujemo
	 * @return da li je uspesno konvertovan fajl
	 */
	public boolean marshalling(String xmlPath, T object) {
		boolean retVal = false;
		try {
			JAXBContext context = JAXBContext.newInstance("xb.model");
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = new File("tem.xml");
			//File file = new File(xmlPath);
			if(!file.exists())
				file.createNewFile();
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			marshaller.marshal(object, fileOutputStream);
			retVal = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	/**
	 * Metoda koja upisuje tekstualni sadrzaj u XML fajl.
	 * @param content sadrzaj koji treba da se upise
	 * @return da li je uspesno izvrseno upisivanje
	 */
	public boolean writeStringToXML(String content) {
		boolean retVal = false;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("tmp.xml");
            fileOutputStream.write(content.getBytes());
            fileOutputStream.close();
            retVal = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
	}

}
