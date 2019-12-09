
package org.me.addresslookup;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.me.addresslookup package. 
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

    private final static QName _AddressLookup_QNAME = new QName("http://addressLookup.me.org/", "addressLookup");
    private final static QName _AddressLookupResponse_QNAME = new QName("http://addressLookup.me.org/", "addressLookupResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.me.addresslookup
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddressLookup }
     * 
     */
    public AddressLookup createAddressLookup() {
        return new AddressLookup();
    }

    /**
     * Create an instance of {@link AddressLookupResponse }
     * 
     */
    public AddressLookupResponse createAddressLookupResponse() {
        return new AddressLookupResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressLookup }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://addressLookup.me.org/", name = "addressLookup")
    public JAXBElement<AddressLookup> createAddressLookup(AddressLookup value) {
        return new JAXBElement<AddressLookup>(_AddressLookup_QNAME, AddressLookup.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressLookupResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://addressLookup.me.org/", name = "addressLookupResponse")
    public JAXBElement<AddressLookupResponse> createAddressLookupResponse(AddressLookupResponse value) {
        return new JAXBElement<AddressLookupResponse>(_AddressLookupResponse_QNAME, AddressLookupResponse.class, null, value);
    }

}
