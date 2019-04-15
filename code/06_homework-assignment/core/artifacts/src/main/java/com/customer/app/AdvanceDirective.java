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
 * A legal document (as a living will or durable power of attorney for healthcare) signed by a competent person to provide guidance for medical and health-care decisions (as the termination of life support or organ donation) in the event the person becomes incompetent to make such decisions.
 * 
 * <p>Java class for AdvanceDirective complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdvanceDirective"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="category" type="{http://www.app.customer.com}Code" minOccurs="0"/&gt;
 *         &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="datelastverified" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="effectivedaterange" type="{http://www.app.customer.com}TimeInterval" minOccurs="0"/&gt;
 *         &lt;element name="image" type="{http://www.app.customer.com}Image" minOccurs="0"/&gt;
 *         &lt;element name="paperlocator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.app.customer.com}Code" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdvanceDirective", propOrder = {
    "category",
    "comments",
    "datelastverified",
    "effectivedaterange",
    "image",
    "paperlocator",
    "status"
})
@XmlSeeAlso({
    PowerOfAttorney.class
})
public class AdvanceDirective
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    protected Code category;
    protected String comments;
    protected Integer datelastverified;
    protected TimeInterval effectivedaterange;
    protected Image image;
    protected String paperlocator;
    protected Code status;

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link Code }
     *     
     */
    public Code getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link Code }
     *     
     */
    public void setCategory(Code value) {
        this.category = value;
    }

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComments(String value) {
        this.comments = value;
    }

    /**
     * Gets the value of the datelastverified property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDatelastverified() {
        return datelastverified;
    }

    /**
     * Sets the value of the datelastverified property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDatelastverified(Integer value) {
        this.datelastverified = value;
    }

    /**
     * Gets the value of the effectivedaterange property.
     * 
     * @return
     *     possible object is
     *     {@link TimeInterval }
     *     
     */
    public TimeInterval getEffectivedaterange() {
        return effectivedaterange;
    }

    /**
     * Sets the value of the effectivedaterange property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeInterval }
     *     
     */
    public void setEffectivedaterange(TimeInterval value) {
        this.effectivedaterange = value;
    }

    /**
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     {@link Image }
     *     
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     {@link Image }
     *     
     */
    public void setImage(Image value) {
        this.image = value;
    }

    /**
     * Gets the value of the paperlocator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaperlocator() {
        return paperlocator;
    }

    /**
     * Sets the value of the paperlocator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaperlocator(String value) {
        this.paperlocator = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Code }
     *     
     */
    public Code getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Code }
     *     
     */
    public void setStatus(Code value) {
        this.status = value;
    }

}
