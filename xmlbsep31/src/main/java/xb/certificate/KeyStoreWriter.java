package xb.certificate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;

import xb.controller.api.FirstController;
import xb.model.Korisnici;
import xb.model.TipKorisnik;

public class KeyStoreWriter {
	
	private KeyStore keyStore;
	
	public KeyStore getKeyStore() {
		return keyStore;
	}

	public KeyStoreWriter() {
		try {
			keyStore = KeyStore.getInstance("JKS");
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
	}
	
	public void loadKeyStore(String fileName, char[] password) {
		try {
			if(fileName != null) 
				keyStore.load(new FileInputStream(fileName), password);
			else
				//ako nema postojeceg KS fajla u koji cemo dodavati, vec se kreira novi,
				//mora da se pozove load, pri cemu je prvi parametar sada null
				keyStore.load(null, password);
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveKeyStore(URI fileName, char[] password) {
		try {
			File file = new File(fileName);
			if(!file.exists())
				file.createNewFile();
			keyStore.store(new FileOutputStream(new File(fileName)), password);
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String alias, PrivateKey privateKey, char[] password, Certificate certificate) {
		try {
			keyStore.setKeyEntry(alias, privateKey, password, new Certificate[] {certificate});
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
	}
	
	public void generateKeyStore(TipKorisnik korisnik) {
		try {
			//kreiramo generator i generisemo kljuceve i sertifiakt
			CertificateGenerator gen = new CertificateGenerator();
			//par kljuceva
			KeyPair keyPair = gen.generateKeyPair();
			
			//datumi
			SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
			String stringStart = iso8601Formater.format(Calendar.getInstance().getTime());
			Date startDate = iso8601Formater.parse(stringStart);
			Date endDate = iso8601Formater.parse("2017-12-31");
			
			//podaci o vlasniku i izdavacu posto je self signed 
			//klasa X500NameBuilder pravi X500Name objekat koji nam treba
			X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		    builder.addRDN(BCStyle.CN, "SGNS");
		    builder.addRDN(BCStyle.SURNAME, "Skupstina Grada Novog Sada");
		    builder.addRDN(BCStyle.GIVENNAME, "SGNS");
		    builder.addRDN(BCStyle.O, "Vlada Republike Srbije");
		    builder.addRDN(BCStyle.OU, "Skupstina Grada Novog Sada");
		    builder.addRDN(BCStyle.C, "RS");
		    builder.addRDN(BCStyle.E, "sgns@rs.gov");
		    //UID (USER ID) je ID korisnika
		    builder.addRDN(BCStyle.UID, "111111");
			
		    //Serijski broj sertifikata
		    Random rand = new Random();
			String sn = String.valueOf(rand.nextInt());
			
			//kreiraju se podaci za issuer-a
			IssuerData issuerData = new IssuerData(keyPair.getPrivate(), builder.build());
			
			//podaci o korisniku kome se izdaje sertifikat
			X500NameBuilder subjBuilder = new X500NameBuilder(BCStyle.INSTANCE);
			subjBuilder.addRDN(BCStyle.CN, korisnik.getKorisnickoIme());
			subjBuilder.addRDN(BCStyle.SURNAME, korisnik.getPrezime());
			subjBuilder.addRDN(BCStyle.GIVENNAME, korisnik.getIme());
			subjBuilder.addRDN(BCStyle.O, "Vlada Republike Srbije");
			subjBuilder.addRDN(BCStyle.OU, "Skupstina Grada Novog Sada");
			subjBuilder.addRDN(BCStyle.C, "RS");
			subjBuilder.addRDN(BCStyle.E, korisnik.getEmail());
		    //UID (USER ID) je ID korisnika
			subjBuilder.addRDN(BCStyle.UID, korisnik.getKorisnickoIme());
			
			//kreiraju se podaci za vlasnika
			SubjectData subjectData = new SubjectData(keyPair.getPublic(), subjBuilder.build(), sn, startDate, endDate);
			
			//generise se sertifikat
			X509Certificate cert = gen.generateCertificate(issuerData, subjectData);
			
			//kreira se keystore, ucitava ks fajl, dodaje kljuc i sertifikat i sacuvaju se izmene
			KeyStoreWriter keyStoreWriter = new KeyStoreWriter();
			keyStoreWriter.loadKeyStore(null, korisnik.getKorisnickoIme().toCharArray());
			keyStoreWriter.write(korisnik.getKorisnickoIme(), keyPair.getPrivate(), korisnik.getLozinka().toCharArray(), cert);
			keyStoreWriter.saveKeyStore(FirstController.class.getClassLoader().getResource("Keystore/" + korisnik.getKorisnickoIme() + ".jks").toURI(), korisnik.getLozinka().toCharArray());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
