
package payphone.easypay.core.ws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for paymentEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paymentEvent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="paymentEventId" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="type" type="{http://ws.core.easypay.payphone/}paymentEventType" />
 *       &lt;attribute name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="reason" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="urlToOpen" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="accountNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paymentEvent")
public class PaymentEvent {

    @XmlAttribute(name = "paymentEventId")
    protected Long paymentEventId;
    @XmlAttribute(name = "type")
    protected PaymentEventType type;
    @XmlAttribute(name = "amount")
    protected BigDecimal amount;
    @XmlAttribute(name = "reason")
    protected String reason;
    @XmlAttribute(name = "urlToOpen")
    protected String urlToOpen;
    @XmlAttribute(name = "accountNumber")
    protected String accountNumber;

    /**
     * Gets the value of the paymentEventId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPaymentEventId() {
        return paymentEventId;
    }

    /**
     * Sets the value of the paymentEventId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPaymentEventId(Long value) {
        this.paymentEventId = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentEventType }
     *     
     */
    public PaymentEventType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentEventType }
     *     
     */
    public void setType(PaymentEventType value) {
        this.type = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of the urlToOpen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlToOpen() {
        return urlToOpen;
    }

    /**
     * Sets the value of the urlToOpen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlToOpen(String value) {
        this.urlToOpen = value;
    }

    /**
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

}
