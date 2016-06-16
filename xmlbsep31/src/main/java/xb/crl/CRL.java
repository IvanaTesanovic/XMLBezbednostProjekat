package xb.crl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CRLException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.CRLNumber;
import org.bouncycastle.asn1.x509.CRLReason;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509v2CRLBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CRLConverter;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.x509.extension.AuthorityKeyIdentifierStructure;

import xb.encryption.EncryptKEK;


public class CRL {

	
	private static X509CRL crl = null;
	private KeyStore ks;
	
	static {
        Security.addProvider(new BouncyCastleProvider());
    }
	 public CRL() throws CertificateParsingException, InvalidKeyException, SignatureException, CertIOException {
		 crl = initializeCRL();
	 }
	
	public  X509CRL initializeCRL() throws CertificateParsingException, SignatureException, InvalidKeyException, CertIOException {
		          X509CRL crl = null;
		          X509CRLHolder crlHolder = null;
		         
		  
		         //X500Name rootName = getRootData();
		          //TODO procitati rootName
		 
		          Date now = new Date();
		          Calendar cal = Calendar.getInstance();
		          cal.setTimeInMillis(0);
		          cal.set(2016, 12, 30, 1, 1, 0);
		          Date update = cal.getTime();
		          //TODO getRootPrivateKey iz keyStoreManagera
		          //TODO getRootCertificate iz keyStoreManagera

		          //ucitavamo key store
		         try {
		        	  ks = KeyStore.getInstance("JKS", "SUN");
					//ucitavamo podatke
		        	  BufferedInputStream in = new BufferedInputStream(EncryptKEK.openInputStream("sgns.jks"));
		        	  ks.load(in, "sgns".toCharArray());
		          }catch (KeyStoreException e) {
		  			System.out.println("ks error");
		          } catch (NoSuchProviderException e) {
		        	  System.out.println("ks error");
		          } catch (FileNotFoundException e) {
		        	  System.out.println("ks error");
		          } catch (NoSuchAlgorithmException e) {
		        	  System.out.println("ks error");
		          } catch (CertificateException e) {
		        	  System.out.println("ks error");
		          } catch (IOException e) {
		        	  System.out.println("ks error");
		          } 
				  // uzimamo privateKey
		          PrivateKey ret = null;
		          char[] password="sgns".toCharArray();
		          try {
		              if(ks.isKeyEntry("sgns")) {
		                  ret = (PrivateKey)ks.getKey("sgns", password);
		              }
		          } catch (KeyStoreException e) {
		              System.out.println("[ERROR] Can't initialize.");
		          } catch (NoSuchAlgorithmException e) {
		        	  System.out.println("[ERROR] Can't initialize.");
		          } catch (UnrecoverableKeyException e) {
		        	  System.out.println("[ERROR] Can't initialize.");
		          }
		          
		          
		          
		          //
		          X509Certificate cert = (X509Certificate) readRootCertificate();
		          X500Name rootName=null;
		          try {
		        	  rootName = new JcaX509CertificateHolder(cert).getSubject();
					} catch (CertificateEncodingException e) {
						
						e.printStackTrace();
					}
		          cert.getSerialNumber();
		          X509v2CRLBuilder crlBuilder = new X509v2CRLBuilder(rootName,now);
		 
		          // 9 -
		  
		          crlBuilder.addCRLEntry(cert.getSerialNumber(),update, CRLReason.privilegeWithdrawn);
		          crlBuilder.addExtension(X509Extensions.AuthorityKeyIdentifier,false,new AuthorityKeyIdentifierStructure(cert));
		          crlBuilder.addExtension(X509Extensions.CRLNumber,false,new CRLNumber(BigInteger.ONE));
		 
		          JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");
		            //koji provider se koristi
		          builder = builder.setProvider("BC");
		          JcaX509CRLConverter converter = new JcaX509CRLConverter();
		          converter.setProvider("BC");

		          try {
					crl = converter.getCRL(crlHolder);
				} catch (CRLException e) {
					e.printStackTrace();
				}
		  
		          return crl;
		      }

	
	 public boolean isRevoked(Certificate cert){
	        boolean ret = false;
	        try{
	            ret = crl.isRevoked(cert);
	        } catch (Exception e){
	        	System.out.println("[ERROR] Can't verify certificate!");
	           
	        }
	        return ret;
	    }

	    /**
	     * Store CRL to file.
	     * @return Indicator of success.
	     */
	    public boolean store()  {
	        try {
	            FileOutputStream fileOutputStream = new FileOutputStream(new File("list.crl"));
	            fileOutputStream.write(crl.getEncoded());
	            fileOutputStream.close();
	            return true;
	        } catch (FileNotFoundException e){
	            System.out.println("[ERROR] Could not store crl to file.");
	            return false;
	        } catch (CRLException e) {
	        	System.out.println("[ERROR] Could not store crl to file.");
	            return false;
	        } catch (IOException e) {
	        	System.out.println("[ERROR] Could not store crl to file.");
	            return false;
	        }

	    }

	    /**
	     * Load CRL from file.
	     * @return Indicator of success.
	     */
	    public boolean load(){
	        try{
	            FileInputStream fileInputStream = new FileInputStream(new File("list.crl"));
	            return true;
	        } catch (Exception e){
	        	System.out.println("[ERROR] Could not load crl from file.");
	            return false;
	        }
	    }
	    
	    private static Certificate readRootCertificate() {
			try {
				//kreiramo instancu KeyStore
				KeyStore ks = KeyStore.getInstance("JKS", "SUN");
				//ucitavamo podatke
				BufferedInputStream in = new BufferedInputStream(EncryptKEK.openInputStream("sgns.jks"));
				ks.load(in, "sgns".toCharArray());
				
				if(ks.isKeyEntry("sgns")) {
					Certificate cert = ks.getCertificate("sgns");
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
		
	    
	    /*public PrivateKey readPrivateKey(String alias, char[] password){
	        PrivateKey ret = null;
	        try {
	            if(ks.isKeyEntry(alias)) {
	                ret = (PrivateKey)ks.getKey(alias, password);
	            }
	        } catch (KeyStoreException e) {
	           System.out.println("[ERROR] Can't initialize.");
	        } catch (NoSuchAlgorithmException e) {
	        	 System.out.println("[ERROR] Can't initialize.");
	        } catch (UnrecoverableKeyException e) {
	        	 System.out.println("[ERROR] Can't initialize.");
	           
	        }
	        return ret;
	    }
	    */
	 	/* public X500Name getRootData(){
	         X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
	         builder.addRDN(BCStyle.CN, "Skupstina grada Novog Sada");
	         builder.addRDN(BCStyle.SURNAME, "Gradska");
	         builder.addRDN(BCStyle.GIVENNAME, "Skupstina");
	         builder.addRDN(BCStyle.O, "Grad Novi Sad");
	         builder.addRDN(BCStyle.OU, "Skupstina grada");
	         builder.addRDN(BCStyle.C, "RS");
	         builder.addRDN(BCStyle.E, "gradskaSkupstina@novisad.rs");
	         //UID (USER ID) je ID korisnika
	         builder.addRDN(BCStyle.UID, "123445");
	         //Serijski broj sertifikata
	         String sn="1";
	         return builder.build();
	     }    */
	 	 
	 	 
	 	
	    
}
