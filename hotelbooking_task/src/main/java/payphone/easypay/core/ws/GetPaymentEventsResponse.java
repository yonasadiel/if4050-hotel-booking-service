
package payphone.easypay.core.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getPaymentEventsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPaymentEventsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://ws.core.easypay.payphone/}paymentEventsBlock" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPaymentEventsResponse", propOrder = {
    "_return"
})
public class GetPaymentEventsResponse {

    @XmlElement(name = "return")
    protected PaymentEventsBlock _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentEventsBlock }
     *     
     */
    public PaymentEventsBlock getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentEventsBlock }
     *     
     */
    public void setReturn(PaymentEventsBlock value) {
        this._return = value;
    }

}
