
package payphone.easypay.core.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getPaymentEvents complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPaymentEvents">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paymentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastEventId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPaymentEvents", propOrder = {
    "paymentId",
    "lastEventId"
})
public class GetPaymentEvents {

    protected String paymentId;
    protected Long lastEventId;

    /**
     * Gets the value of the paymentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the value of the paymentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentId(String value) {
        this.paymentId = value;
    }

    /**
     * Gets the value of the lastEventId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLastEventId() {
        return lastEventId;
    }

    /**
     * Sets the value of the lastEventId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLastEventId(Long value) {
        this.lastEventId = value;
    }

}
