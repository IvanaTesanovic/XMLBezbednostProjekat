//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.12 at 09:57:48 PM CEST 
//


package xb.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xb.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ClanRef_QNAME = new QName("http://www.skupstinans.rs", "Ref");
    private final static QName _ClanStav_QNAME = new QName("http://www.skupstinans.rs", "Stav");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xb.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Zakon }
     * 
     */
    public Zakon createZakon() {
        return new Zakon();
    }

    /**
     * Create an instance of {@link Clan }
     * 
     */
    public Clan createClan() {
        return new Clan();
    }

    /**
     * Create an instance of {@link Clan.Stav }
     * 
     */
    public Clan.Stav createClanStav() {
        return new Clan.Stav();
    }

    /**
     * Create an instance of {@link Clan.Stav.Tacka }
     * 
     */
    public Clan.Stav.Tacka createClanStavTacka() {
        return new Clan.Stav.Tacka();
    }

    /**
     * Create an instance of {@link Clan.Stav.Tacka.Podtacka }
     * 
     */
    public Clan.Stav.Tacka.Podtacka createClanStavTackaPodtacka() {
        return new Clan.Stav.Tacka.Podtacka();
    }

    /**
     * Create an instance of {@link Zakon.Deo }
     * 
     */
    public Zakon.Deo createZakonDeo() {
        return new Zakon.Deo();
    }

    /**
     * Create an instance of {@link Zakon.Deo.Glava }
     * 
     */
    public Zakon.Deo.Glava createZakonDeoGlava() {
        return new Zakon.Deo.Glava();
    }

    /**
     * Create an instance of {@link Zakon.Deo.Glava.Odeljak }
     * 
     */
    public Zakon.Deo.Glava.Odeljak createZakonDeoGlavaOdeljak() {
        return new Zakon.Deo.Glava.Odeljak();
    }

    /**
     * Create an instance of {@link Clan.Stav.Tacka.Podtacka.Alineja }
     * 
     */
    public Clan.Stav.Tacka.Podtacka.Alineja createClanStavTackaPodtackaAlineja() {
        return new Clan.Stav.Tacka.Podtacka.Alineja();
    }

    /**
     * Create an instance of {@link Zakon.Deo.Glava.Odeljak.Pododeljak }
     * 
     */
    public Zakon.Deo.Glava.Odeljak.Pododeljak createZakonDeoGlavaOdeljakPododeljak() {
        return new Zakon.Deo.Glava.Odeljak.Pododeljak();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.skupstinans.rs", name = "Ref", scope = Clan.class)
    public JAXBElement<Object> createClanRef(Object value) {
        return new JAXBElement<Object>(_ClanRef_QNAME, Object.class, Clan.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Clan.Stav }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.skupstinans.rs", name = "Stav", scope = Clan.class)
    public JAXBElement<Clan.Stav> createClanStav(Clan.Stav value) {
        return new JAXBElement<Clan.Stav>(_ClanStav_QNAME, Clan.Stav.class, Clan.class, value);
    }
    
    /**
     * Create an instance of {@link Korisnici }
     * 
     */
    public Korisnici createKorisnici() {
        return new Korisnici();
    }

    /**
     * Create an instance of {@link TipKorisnik }
     * 
     */
    public TipKorisnik createTipKorisnik() {
        return new TipKorisnik();
    }

}
