
package org.me.calculatepayout;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.me.calculatepayout package. 
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

    private final static QName _CalculatePayout_QNAME = new QName("http://calculatePayout.me.org/", "calculatePayout");
    private final static QName _CalculatePayoutResponse_QNAME = new QName("http://calculatePayout.me.org/", "calculatePayoutResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.me.calculatepayout
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CalculatePayout }
     * 
     */
    public CalculatePayout createCalculatePayout() {
        return new CalculatePayout();
    }

    /**
     * Create an instance of {@link CalculatePayoutResponse }
     * 
     */
    public CalculatePayoutResponse createCalculatePayoutResponse() {
        return new CalculatePayoutResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculatePayout }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://calculatePayout.me.org/", name = "calculatePayout")
    public JAXBElement<CalculatePayout> createCalculatePayout(CalculatePayout value) {
        return new JAXBElement<CalculatePayout>(_CalculatePayout_QNAME, CalculatePayout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculatePayoutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://calculatePayout.me.org/", name = "calculatePayoutResponse")
    public JAXBElement<CalculatePayoutResponse> createCalculatePayoutResponse(CalculatePayoutResponse value) {
        return new JAXBElement<CalculatePayoutResponse>(_CalculatePayoutResponse_QNAME, CalculatePayoutResponse.class, null, value);
    }

}
