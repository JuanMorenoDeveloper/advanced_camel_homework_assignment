//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.11 at 03:31:19 PM UYT 
//


package com.customer.app;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Code 
 * 
 * <p>Java class for Code complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Code"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="displaytext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codesystem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="codesystemversion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Code", propOrder = {
    "code",
    "displaytext",
    "codesystem",
    "codesystemversion"
})
@XmlSeeAlso({
    CodedOrdinal.class,
    CodeWithOriginalText.class
})
public class Code
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    protected String code;
    protected String displaytext;
    protected String codesystem;
    protected String codesystemversion;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the displaytext property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplaytext() {
        return displaytext;
    }

    /**
     * Sets the value of the displaytext property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplaytext(String value) {
        this.displaytext = value;
    }

    /**
     * Gets the value of the codesystem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodesystem() {
        return codesystem;
    }

    /**
     * Sets the value of the codesystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodesystem(String value) {
        this.codesystem = value;
    }

    /**
     * Gets the value of the codesystemversion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodesystemversion() {
        return codesystemversion;
    }

    /**
     * Sets the value of the codesystemversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodesystemversion(String value) {
        this.codesystemversion = value;
    }

}
