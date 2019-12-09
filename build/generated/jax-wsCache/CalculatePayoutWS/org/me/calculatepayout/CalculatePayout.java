
package org.me.calculatepayout;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for calculatePayout complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="calculatePayout"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="i" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="j" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculatePayout", propOrder = {
    "i",
    "j"
})
public class CalculatePayout {

    protected double i;
    protected double j;

    /**
     * Gets the value of the i property.
     * 
     */
    public double getI() {
        return i;
    }

    /**
     * Sets the value of the i property.
     * 
     */
    public void setI(double value) {
        this.i = value;
    }

    /**
     * Gets the value of the j property.
     * 
     */
    public double getJ() {
        return j;
    }

    /**
     * Sets the value of the j property.
     * 
     */
    public void setJ(double value) {
        this.j = value;
    }

}
