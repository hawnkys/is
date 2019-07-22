
package artifact;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the artifact package. 
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

    private final static QName _AddFollowerResponse_QNAME = new QName("http://is_project3/", "addFollowerResponse");
    private final static QName _ListAll_QNAME = new QName("http://is_project3/", "listAll");
    private final static QName _DeleteFollower_QNAME = new QName("http://is_project3/", "deleteFollower");
    private final static QName _ListUsers_QNAME = new QName("http://is_project3/", "listUsers");
    private final static QName _ListAllResponse_QNAME = new QName("http://is_project3/", "listAllResponse");
    private final static QName _AddFollower_QNAME = new QName("http://is_project3/", "addFollower");
    private final static QName _DeleteFollowerResponse_QNAME = new QName("http://is_project3/", "deleteFollowerResponse");
    private final static QName _ListUsersResponse_QNAME = new QName("http://is_project3/", "listUsersResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: artifact
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeleteFollowerResponse }
     * 
     */
    public DeleteFollowerResponse createDeleteFollowerResponse() {
        return new DeleteFollowerResponse();
    }

    /**
     * Create an instance of {@link ListUsersResponse }
     * 
     */
    public ListUsersResponse createListUsersResponse() {
        return new ListUsersResponse();
    }

    /**
     * Create an instance of {@link ListAllResponse }
     * 
     */
    public ListAllResponse createListAllResponse() {
        return new ListAllResponse();
    }

    /**
     * Create an instance of {@link AddFollower }
     * 
     */
    public AddFollower createAddFollower() {
        return new AddFollower();
    }

    /**
     * Create an instance of {@link DeleteFollower }
     * 
     */
    public DeleteFollower createDeleteFollower() {
        return new DeleteFollower();
    }

    /**
     * Create an instance of {@link ListUsers }
     * 
     */
    public ListUsers createListUsers() {
        return new ListUsers();
    }

    /**
     * Create an instance of {@link ListAll }
     * 
     */
    public ListAll createListAll() {
        return new ListAll();
    }

    /**
     * Create an instance of {@link AddFollowerResponse }
     * 
     */
    public AddFollowerResponse createAddFollowerResponse() {
        return new AddFollowerResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddFollowerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://is_project3/", name = "addFollowerResponse")
    public JAXBElement<AddFollowerResponse> createAddFollowerResponse(AddFollowerResponse value) {
        return new JAXBElement<AddFollowerResponse>(_AddFollowerResponse_QNAME, AddFollowerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://is_project3/", name = "listAll")
    public JAXBElement<ListAll> createListAll(ListAll value) {
        return new JAXBElement<ListAll>(_ListAll_QNAME, ListAll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteFollower }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://is_project3/", name = "deleteFollower")
    public JAXBElement<DeleteFollower> createDeleteFollower(DeleteFollower value) {
        return new JAXBElement<DeleteFollower>(_DeleteFollower_QNAME, DeleteFollower.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListUsers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://is_project3/", name = "listUsers")
    public JAXBElement<ListUsers> createListUsers(ListUsers value) {
        return new JAXBElement<ListUsers>(_ListUsers_QNAME, ListUsers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListAllResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://is_project3/", name = "listAllResponse")
    public JAXBElement<ListAllResponse> createListAllResponse(ListAllResponse value) {
        return new JAXBElement<ListAllResponse>(_ListAllResponse_QNAME, ListAllResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddFollower }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://is_project3/", name = "addFollower")
    public JAXBElement<AddFollower> createAddFollower(AddFollower value) {
        return new JAXBElement<AddFollower>(_AddFollower_QNAME, AddFollower.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteFollowerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://is_project3/", name = "deleteFollowerResponse")
    public JAXBElement<DeleteFollowerResponse> createDeleteFollowerResponse(DeleteFollowerResponse value) {
        return new JAXBElement<DeleteFollowerResponse>(_DeleteFollowerResponse_QNAME, DeleteFollowerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://is_project3/", name = "listUsersResponse")
    public JAXBElement<ListUsersResponse> createListUsersResponse(ListUsersResponse value) {
        return new JAXBElement<ListUsersResponse>(_ListUsersResponse_QNAME, ListUsersResponse.class, null, value);
    }

}
