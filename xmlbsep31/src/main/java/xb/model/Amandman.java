//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.17 at 01:15:34 AM CEST 
//


package xb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Predlozeno_resenje" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Sadrzaj_resenja" type="{http://www.skupstinans.rs}TOdredba"/>
 *         &lt;element name="ID_akta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ID_odredbe" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "predlozenoResenje",
    "sadrzajResenja",
    "idAkta",
    "idOdredbe"
})
@XmlRootElement(name = "Amandman")
public class Amandman {

    @XmlElement(name = "Predlozeno_resenje", required = true)
    protected String predlozenoResenje;
    @XmlElement(name = "Sadrzaj_resenja", required = true)
    protected TOdredba sadrzajResenja;
    @XmlElement(name = "ID_akta", required = true)
    protected String idAkta;
    @XmlElement(name = "ID_odredbe", required = true)
    protected String idOdredbe;

    /**
     * Gets the value of the predlozenoResenje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPredlozenoResenje() {
        return predlozenoResenje;
    }

    /**
     * Sets the value of the predlozenoResenje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPredlozenoResenje(String value) {
        this.predlozenoResenje = value;
    }

    /**
     * Gets the value of the sadrzajResenja property.
     * 
     * @return
     *     possible object is
     *     {@link TOdredba }
     *     
     */
    public TOdredba getSadrzajResenja() {
        return sadrzajResenja;
    }

    /**
     * Sets the value of the sadrzajResenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOdredba }
     *     
     */
    public void setSadrzajResenja(TOdredba value) {
        this.sadrzajResenja = value;
    }

    /**
     * Gets the value of the idAkta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDAkta() {
        return idAkta;
    }

    /**
     * Sets the value of the idAkta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDAkta(String value) {
        this.idAkta = value;
    }

    /**
     * Gets the value of the idOdredbe property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDOdredbe() {
        return idOdredbe;
    }

    /**
     * Sets the value of the idOdredbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDOdredbe(String value) {
        this.idOdredbe = value;
    }

}
