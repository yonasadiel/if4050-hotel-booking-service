
package payphone.easypay.core.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the payphone.easypay.core.ws package. 
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

    private final static QName _GetPaymentEventsResponse_QNAME = new QName("http://ws.core.payphone/", "getPaymentEventsResponse");
    private final static QName _PaymentEventsBlock_QNAME = new QName("http://ws.core.payphone/", "paymentEventsBlock");
    private final static QName _GetPaymentMethods_QNAME = new QName("http://ws.core.payphone/", "getPaymentMethods");
    private final static QName _BeginPayment_QNAME = new QName("http://ws.core.payphone/", "beginPayment");
    private final static QName _GetPaymentMethodsResponse_QNAME = new QName("http://ws.core.payphone/", "getPaymentMethodsResponse");
    private final static QName _PaymentEvent_QNAME = new QName("http://ws.core.payphone/", "paymentEvent");
    private final static QName _GetPaymentEvents_QNAME = new QName("http://ws.core.payphone/", "getPaymentEvents");
    private final static QName _PaymentMethod_QNAME = new QName("http://ws.core.payphone/", "paymentMethod");
    private final static QName _BeginPaymentResponse_QNAME = new QName("http://ws.core.payphone/", "beginPaymentResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: payphone.easypay.core.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPaymentEventsResponse }
     * 
     */
    public GetPaymentEventsResponse createGetPaymentEventsResponse() {
        return new GetPaymentEventsResponse();
    }

    /**
     * Create an instance of {@link PaymentEventsBlock }
     * 
     */
    public PaymentEventsBlock createPaymentEventsBlock() {
        return new PaymentEventsBlock();
    }

    /**
     * Create an instance of {@link GetPaymentMethods }
     * 
     */
    public GetPaymentMethods createGetPaymentMethods() {
        return new GetPaymentMethods();
    }

    /**
     * Create an instance of {@link BeginPayment }
     * 
     */
    public BeginPayment createBeginPayment() {
        return new BeginPayment();
    }

    /**
     * Create an instance of {@link GetPaymentMethodsResponse }
     * 
     */
    public GetPaymentMethodsResponse createGetPaymentMethodsResponse() {
        return new GetPaymentMethodsResponse();
    }

    /**
     * Create an instance of {@link PaymentEvent }
     * 
     */
    public PaymentEvent createPaymentEvent() {
        return new PaymentEvent();
    }

    /**
     * Create an instance of {@link GetPaymentEvents }
     * 
     */
    public GetPaymentEvents createGetPaymentEvents() {
        return new GetPaymentEvents();
    }

    /**
     * Create an instance of {@link PaymentMethod }
     * 
     */
    public PaymentMethod createPaymentMethod() {
        return new PaymentMethod();
    }

    /**
     * Create an instance of {@link BeginPaymentResponse }
     * 
     */
    public BeginPaymentResponse createBeginPaymentResponse() {
        return new BeginPaymentResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPaymentEventsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.core.payphone/", name = "getPaymentEventsResponse")
    public JAXBElement<GetPaymentEventsResponse> createGetPaymentEventsResponse(GetPaymentEventsResponse value) {
        return new JAXBElement<GetPaymentEventsResponse>(_GetPaymentEventsResponse_QNAME, GetPaymentEventsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaymentEventsBlock }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.core.payphone/", name = "paymentEventsBlock")
    public JAXBElement<PaymentEventsBlock> createPaymentEventsBlock(PaymentEventsBlock value) {
        return new JAXBElement<PaymentEventsBlock>(_PaymentEventsBlock_QNAME, PaymentEventsBlock.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPaymentMethods }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.core.payphone/", name = "getPaymentMethods")
    public JAXBElement<GetPaymentMethods> createGetPaymentMethods(GetPaymentMethods value) {
        return new JAXBElement<GetPaymentMethods>(_GetPaymentMethods_QNAME, GetPaymentMethods.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BeginPayment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.core.payphone/", name = "beginPayment")
    public JAXBElement<BeginPayment> createBeginPayment(BeginPayment value) {
        return new JAXBElement<BeginPayment>(_BeginPayment_QNAME, BeginPayment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPaymentMethodsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.core.payphone/", name = "getPaymentMethodsResponse")
    public JAXBElement<GetPaymentMethodsResponse> createGetPaymentMethodsResponse(GetPaymentMethodsResponse value) {
        return new JAXBElement<GetPaymentMethodsResponse>(_GetPaymentMethodsResponse_QNAME, GetPaymentMethodsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaymentEvent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.core.payphone/", name = "paymentEvent")
    public JAXBElement<PaymentEvent> createPaymentEvent(PaymentEvent value) {
        return new JAXBElement<PaymentEvent>(_PaymentEvent_QNAME, PaymentEvent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPaymentEvents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.core.payphone/", name = "getPaymentEvents")
    public JAXBElement<GetPaymentEvents> createGetPaymentEvents(GetPaymentEvents value) {
        return new JAXBElement<GetPaymentEvents>(_GetPaymentEvents_QNAME, GetPaymentEvents.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaymentMethod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.core.payphone/", name = "paymentMethod")
    public JAXBElement<PaymentMethod> createPaymentMethod(PaymentMethod value) {
        return new JAXBElement<PaymentMethod>(_PaymentMethod_QNAME, PaymentMethod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BeginPaymentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.core.payphone/", name = "beginPaymentResponse")
    public JAXBElement<BeginPaymentResponse> createBeginPaymentResponse(BeginPaymentResponse value) {
        return new JAXBElement<BeginPaymentResponse>(_BeginPaymentResponse_QNAME, BeginPaymentResponse.class, null, value);
    }

}
