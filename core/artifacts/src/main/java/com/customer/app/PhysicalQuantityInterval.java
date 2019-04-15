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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This data type represents an Interval, where the Low and High Limits are Physical Quantities. An Interval is a "set of consecutive values of an ordered base data type." - HL7 V3 A Physical Quantity is "a dimensioned quantity expressing the result of measuring" - HL7 V3
 * 
 * <p>Java class for PhysicalQuantityInterval complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PhysicalQuantityInterval"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="highunit" type="{http://www.app.customer.com}Code" minOccurs="0"/&gt;
 *         &lt;element name="highvalue" type="{http://www.app.customer.com}Real" minOccurs="0"/&gt;
 *         &lt;element name="lowunit" type="{http://www.app.customer.com}Code" minOccurs="0"/&gt;
 *         &lt;element name="lowvalue" type="{http://www.app.customer.com}Real" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhysicalQuantityInterval", propOrder = {
    "highunit",
    "highvalue",
    "lowunit",
    "lowvalue"
})
@XmlRootElement(name = "PhysicalQuantityInterval")
public class PhysicalQuantityInterval
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    protected Code highunit;
    protected Real highvalue;
    protected Code lowunit;
    protected Real lowvalue;

    /**
     * Gets the value of the highunit property.
     * 
     * @return
     *     possible object is
     *     {@link Code }
     *     
     */
    public Code getHighunit() {
        return highunit;
    }

    /**
     * Sets the value of the highunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Code }
     *     
     */
    public void setHighunit(Code value) {
        this.highunit = value;
    }

    /**
     * Gets the value of the highvalue property.
     * 
     * @return
     *     possible object is
     *     {@link Real }
     *     
     */
    public Real getHighvalue() {
        return highvalue;
    }

    /**
     * Sets the value of the highvalue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Real }
     *     
     */
    public void setHighvalue(Real value) {
        this.highvalue = value;
    }

    /**
     * Gets the value of the lowunit property.
     * 
     * @return
     *     possible object is
     *     {@link Code }
     *     
     */
    public Code getLowunit() {
        return lowunit;
    }

    /**
     * Sets the value of the lowunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Code }
     *     
     */
    public void setLowunit(Code value) {
        this.lowunit = value;
    }

    /**
     * Gets the value of the lowvalue property.
     * 
     * @return
     *     possible object is
     *     {@link Real }
     *     
     */
    public Real getLowvalue() {
        return lowvalue;
    }

    /**
     * Sets the value of the lowvalue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Real }
     *     
     */
    public void setLowvalue(Real value) {
        this.lowvalue = value;
    }

}