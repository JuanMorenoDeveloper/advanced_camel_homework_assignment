
package com.sun.mdm.index.webservice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for lookupSystemRecordUIDsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="lookupSystemRecordUIDsResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://webservice.index.mdm.sun.com/}systemObjectUIDBean" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lookupSystemRecordUIDsResponse", propOrder = {
    "returns"
})
@XmlRootElement(name = "lookupSystemRecordUIDsResponse")
public class LookupSystemRecordUIDsResponse
    implements Serializable
{

    private final static long serialVersionUID = 12343L;
    @XmlElement(name = "return", nillable = true)
    protected List<SystemObjectUIDBean> returns;

    /**
     * Gets the value of the returns property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the returns property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReturns().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SystemObjectUIDBean }
     * 
     * 
     */
    public List<SystemObjectUIDBean> getReturns() {
        if (returns == null) {
            returns = new ArrayList<SystemObjectUIDBean>();
        }
        return this.returns;
    }

    public boolean isSetReturns() {
        return ((this.returns!= null)&&(!this.returns.isEmpty()));
    }

    public void unsetReturns() {
        this.returns = null;
    }

}
