
package payphone.easypay.core.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for paymentEventsBlock complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paymentEventsBlock">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="events" type="{http://ws.core.easypay.payphone/}paymentEvent" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "paymentEventsBlock", propOrder = {
    "events",
    "lastEventId"
})
public class PaymentEventsBlock {

    @XmlElement(nillable = true)
    protected List<PaymentEvent> events;
    protected Long lastEventId;

    /**
     * Gets the value of the events property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the events property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEvents().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentEvent }
     * 
     * 
     */
    public List<PaymentEvent> getEvents() {
        if (events == null) {
            events = new ArrayList<PaymentEvent>();
        }
        return this.events;
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
