//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.24 at 12:08:07 AM EET 
//


package JAXB;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the JAXB package. 
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

    private final static QName _ChatSession_QNAME = new QName("", "chatSession");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: JAXB
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ChatSessionType }
     * 
     */
    public ChatSessionType createChatSessionType() {
        return new ChatSessionType();
    }

    /**
     * Create an instance of {@link MessageType }
     * 
     */
    public MessageType createMessageType() {
        return new MessageType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChatSessionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "chatSession")
    public JAXBElement<ChatSessionType> createChatSession(ChatSessionType value) {
        return new JAXBElement<ChatSessionType>(_ChatSession_QNAME, ChatSessionType.class, null, value);
    }

}