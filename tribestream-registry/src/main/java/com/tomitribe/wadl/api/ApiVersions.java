//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.27 at 12:09:46 AM EET 
//


package com.tomitribe.wadl.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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
 *         &lt;element ref="{http://www.tomitribe.com/schemas/2014/02/wadl/extension/other}api-version" maxOccurs="unbounded" minOccurs="0"/>
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
    "apiVersion"
})
@XmlRootElement(name = "api-versions", namespace = "http://www.tomitribe.com/schemas/2014/02/wadl/extension/other")
public class ApiVersions
    implements Serializable
{

    @XmlElement(name = "api-version", namespace = "http://www.tomitribe.com/schemas/2014/02/wadl/extension/other")
    protected List<String> apiVersion;

    /**
     * Gets the value of the apiVersion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the apiVersion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApiVersion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getApiVersion() {
        if (apiVersion == null) {
            apiVersion = new ArrayList<String>();
        }
        return this.apiVersion;
    }

    public ApiVersions withApiVersion(String... values) {
        if (values!= null) {
            for (String value: values) {
                getApiVersion().add(value);
            }
        }
        return this;
    }

    public ApiVersions withApiVersion(Collection<String> values) {
        if (values!= null) {
            getApiVersion().addAll(values);
        }
        return this;
    }

}
