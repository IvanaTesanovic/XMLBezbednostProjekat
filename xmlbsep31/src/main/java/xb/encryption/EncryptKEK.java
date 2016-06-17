package xb.encryption;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xml.security.encryption.EncryptedData;
import org.apache.xml.security.encryption.EncryptedKey;
import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.encryption.XMLEncryptionException;
import org.apache.xml.security.keys.KeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import xb.signing.SignEnveloped;

//Generise tajni kljuc
//Kriptije sadrzaj elementa student tajnim kljucem
//Kriptuje tajni kljuc javnim kljucem
//Kriptovani tajni kljuc se stavlja kao KeyInfo kriptovanog elementa
public class EncryptKEK {
	
	//putanja do foldera sa mojim keystore file-ovima, odnosno sertifikatima
	/** PROMENITI **/
	private static final String KEY_STORE_FILE = "./data/primer.jks";
	
    static {
        Security.addProvider(new BouncyCastleProvider());
        org.apache.xml.security.Init.init();
    }
    
    /**
     * Ekripcija dokumenta.
     * @param doc dokument koji se enkriptuje
     * @param naziv naziv .jks fajla sertifikata
     */
//    public Document encryptDocument(Document doc, String naziv) {
//    	//ucitavamo dokument za kriptovanje
//    	//Document doc = loadDocument(path);
//    	//generisemo tajni kljuc
//    	SecretKey secretKey = generateDataEncryptionKey();
//    	//ucitavamo sertifikat za kriptovanje
//    	Certificate cert = readCertificate(naziv);
//    	/** kriptujemo dokument **/
//    	doc = encrypt(doc, secretKey, cert);
//		//snima se tajni kljuc
//		//snima se dokument
//		//saveDocument(doc, OUT_FILE);
//    	return doc;
//    }
    
    /**
	 * Kreira DOM od XML dokumenta
	 */
	public Document loadDocument(InputStream file) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(file);

			return document;
		} catch (FactoryConfigurationError e) {
			e.printStackTrace();
			return null;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Ucitava sertifikat is KS fajla
	 * alias primer (izmeniti)
	 */
	public Certificate readCertificate() {
		try {
			//kreiramo instancu KeyStore
			KeyStore ks = KeyStore.getInstance("JKS", "SUN");
			//ucitavamo podatke
			BufferedInputStream in = new BufferedInputStream(openInputStream("primer.jks"));
			ks.load(in, "primer".toCharArray());
			
			if(ks.isKeyEntry("primer")) {
				Certificate cert = ks.getCertificate("primer");
				return cert;
			}
			else
				return null;
			
		} catch (KeyStoreException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			return null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (CertificateException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	/**
	 * Generise tajni kljuc (koristi se AES algoritam)
	 */
	public SecretKey generateDataEncryptionKey() {

        try {
			//KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede"); //Triple DES
        	KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			return keyGenerator.generateKey();
		
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
    }
	
	/**
	 * Kriptuje sadrzaj prvog elementa (uvek se sifruje sadrzaj korenskog elementa poruke!)
	 */
	public Document encrypt(Document doc, SecretKey key, Certificate certificate) {
		
		try {
			//cipher za kriptovanje tajnog kljuca, koristi se Javni RSA kljuc za kriptovanje
			XMLCipher keyCipher = XMLCipher.getInstance(XMLCipher.RSA_v1dot5);
		    //inicijalizacija za kriptovanje tajnog kljuca javnim RSA kljucem
		    keyCipher.init(XMLCipher.WRAP_MODE, certificate.getPublicKey());
		    EncryptedKey encryptedKey = keyCipher.encryptKey(doc, key);
			
		    //cipher za kriptovanje XML-a
		    //XMLCipher xmlCipher = XMLCipher.getInstance(XMLCipher.TRIPLEDES);
		    XMLCipher xmlCipher = XMLCipher.getInstance(XMLCipher.AES_128);
		    //inicijalizacija za kriptovanje
		    xmlCipher.init(XMLCipher.ENCRYPT_MODE, key);
		    
		    //u EncryptedData element koji se kriptuje, kao KeyInfo stavljamo kriptovan tajni kljuc
		    EncryptedData encryptedData = xmlCipher.getEncryptedData();
	        //kreira se KeyInfo (ukazuje na kljuc koji se koristi za validaciju potpisa)
		    KeyInfo keyInfo = new KeyInfo(doc);
		    keyInfo.addKeyName("Kriptovani tajni kljuc");
	        //postavljamo kriptovani kljuc
		    keyInfo.add(encryptedKey);
		    //postavljamo KeyInfo za element koji se kriptuje
	        encryptedData.setKeyInfo(keyInfo);
			
			//trazi se element ciji sadrzaj se kriptuje
	        //TODO ovde proveriti za ovaj ns1 sto ide uz zakon
			NodeList akt = doc.getElementsByTagName("Zakon");
			Element odsek = (Element) akt.item(0);
			if(odsek == null)
			   {
				akt = doc.getElementsByTagName("Zakon");
			    odsek = (Element) akt.item(0);
			   }
			
	        //uvek kriptujemo korenski element
			//Element root = (Element) doc.getChildNodes().item(0);
			
			xmlCipher.doFinal(doc, odsek, true); //kriptuje sa sadrzaj
			
			return doc;
			
		} catch (XMLEncryptionException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Snima DOM u XML fajl 
	 */
	public void saveDocument(Document doc, String fileName) {
		try {
			File outFile = new File(fileName);
			FileOutputStream f = new FileOutputStream(outFile);

			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(f);
			
			transformer.transform(source, result);

			f.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static InputStream openInputStream(String name) {
		return SignEnveloped.class.getClassLoader().getResourceAsStream("Keystore/" + name);
	}
	
}
