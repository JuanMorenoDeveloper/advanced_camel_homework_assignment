//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.11 at 03:31:19 PM UYT 
//


package com.customer.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Biological Entity - Person  - 'A physical thing, group of physical things or an organization capable of participating in Acts while in a role. An entity is a physical object that has, had or will have existence. The only exception to this is Organization, which while not having a physical presence, fulfills the other characteristics of an Entity. Entity stipulates the thing itself, not the Roles it may play: the Role of Patient, e.g., is played by the Person Entity.' HL7 RIM.
 * 
 * <p>Java class for Parties complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Parties"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="identifier" type="{http://www.app.customer.com}ID" minOccurs="0"/&gt;
 *         &lt;element name="PartiesAlternateId" type="{http://www.app.customer.com}PartiesAlternateId" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Parties", propOrder = {
    "identifier",
    "partiesAlternateIds"
})
@XmlSeeAlso({
    BiologicalEntity.class
})
public class Parties
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    protected ID identifier;
    @XmlElement(name = "PartiesAlternateId")
    protected List<PartiesAlternateId> partiesAlternateIds;

    /**
     * Gets the value of the identifier property.
     * 
     * @return
     *     possible object is
     *     {@link ID }
     *     
     */
    public ID getIdentifier() {
        return identifier;
    }

    /**
     * Sets the value of the identifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link ID }
     *     
     */
    public void setIdentifier(ID value) {
        this.identifier = value;
    }

    /**
     * Gets the value of the partiesAlternateIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partiesAlternateIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartiesAlternateIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartiesAlternateId }
     * 
     * 
     */
    public List<PartiesAlternateId> getPartiesAlternateIds() {
        if (partiesAlternateIds == null) {
            partiesAlternateIds = new ArrayList<PartiesAlternateId>();
        }
        return this.partiesAlternateIds;
    }

}
