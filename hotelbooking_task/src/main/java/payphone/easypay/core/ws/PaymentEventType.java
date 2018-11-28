
package payphone.easypay.core.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for paymentEventType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="paymentEventType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SUCCESS"/>
 *     &lt;enumeration value="FAILURE"/>
 *     &lt;enumeration value="OPEN_URL"/>
 *     &lt;enumeration value="AMOUNT_CHANGED"/>
 *     &lt;enumeration value="ACCOUNT_NUMBER_AVAILABLE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "paymentEventType")
@XmlEnum
public enum PaymentEventType {

    SUCCESS,
    FAILURE,
    OPEN_URL,
    AMOUNT_CHANGED,
    ACCOUNT_NUMBER_AVAILABLE;

    public String value() {
        return name();
    }

    public static PaymentEventType fromValue(String v) {
        return valueOf(v);
    }

}
