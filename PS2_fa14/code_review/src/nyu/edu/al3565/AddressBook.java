package nyu.edu.al3565;
//!!!!Reference!!!!
/* Reference Code: http://www.mkyong.com/java/how-to-create-xml-file-in-java-do
 * m for write to xml. and the code from the same author for reading from xml.
 */
import com.sun.istack.internal.NotNull;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is an address book.
 * <p>
 *     Each address book has to have its own required name to be distinguished 
 *     from others. Each address book contains a vector of contacts to be used 
 *     later.
 * </p>
 * Created by Greyjoy on 9/21/14.
 */
public class AddressBook {
  private static final Logger log = Logger.getLogger( AddressBook.class.
      getName());
  private String AddressBookName = "";
  private Vector<Contact> Contacts = new Vector<Contact>();

  /**
   * Create a new address book.
   * @param AddressBookName the name of this new address book. Cannot be null.
   */
  public AddressBook(@NotNull String AddressBookName) throws 
  IllegalArgumentException {
    if (AddressBookName == null){
      throw new IllegalArgumentException();
    }
    this.AddressBookName = AddressBookName;
  }

  /**
   * Add a contact to this address book.
   * @param contact The contact to be added/
   * @return 0 if successful.
   */
  public int addContact(Contact contact) {
    Contacts.add(contact);
    return 0;
  }

  /**
   * Search for the interested keyword
   * @param keyWord: a word that should be contained in the contact (anywhere 
   * in the contact) that you want to search.
   * @return a vector of contacts containing the key word. Or if find nothing, 
   * return an empty vector.
   */
  public Vector<Contact> search(String keyWord) throws IllegalArgumentException{

    Vector<Contact> SearchResult = new Vector<Contact>();

    //Forbid using null as a key word to search
    if (keyWord == null){
      throw new IllegalArgumentException();
    }
    if (Contacts.size() == 0) {
      return SearchResult;
    }
    for (int i = 0; i < Contacts.size(); i++) {
      Contact currentContact = Contacts.elementAt(i);
      if (currentContact.containsKeyWord(keyWord)) {
        SearchResult.add(currentContact);
      }
    }
    return SearchResult;
  }

  /**
   * Override to string
   * @return The contacts' name contained in this address book. Or a sentence 
   * indicating empty if the address book is empty.
   */
  @Override
  public String toString() {

    if (Contacts.size() == 0) {
      return "This address book is empty.";
    }

    StringBuffer addressBookOutput = new StringBuffer();

    for (int i = 0; i < Contacts.size(); i++) {
      Contact currentContact = Contacts.elementAt(i);
      addressBookOutput.append(currentContact.returnName() + "\n");
    }
    return addressBookOutput.toString();
  }

  /**
   * This is the entry space of deleting method.
   * <p>
   *     To avoid deleting by accident, user is not allowed to directly delete
   *     an entry (contact) by just giving a keyword. Instead, a searching list
   *     of the keyword entered by the user will be shown here. And the user has
   *     to choose one of them to be deleted from the private delete method 
   *     given below.
   * </p>
   * @param Key Word contained in the entry that the user wants to delete.
   * @return 0 if successful.
   */
  public int deleteContact(@NotNull String keyWord) {
    this.search(keyWord);
    /* After choosing the entry that user wants to delete, the entry can be
        deleted here. Below is a sample deleting, say, delete the fifth entry of
        the contact vector.
     */
    //        this.deleteEntry(5);
    return 0;
  }

  //Delete the entry according to its position in the contact vector.
  private int deleteEntry(int i){
    try {
      Contacts.remove(i);
    }
    catch (ArrayIndexOutOfBoundsException e){
      //Return an error code
      return -1;
    }
    return 0;
  }

  /**
   * Write this address book to a local file.
   * @return 0 if successful.
   */
  public int writeToLocalFile(String Path){

    //Example of path name.
    //Path = "/Users/Greyjoy/Desktop/file.xml";
    try {

      //Test if a file is already existed in the position you 
      File file = new File(Path);

      if (file.exists()){
        log.info("File Already exist");
        return -1;
      }
      else{
        try {
          file.delete();
        } 
        catch(Exception e){
          log.log(Level.WARNING,"Delete Error",e);
          e.printStackTrace();                           
        }
      }

      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

      // root elements
      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement(AddressBookName);
      doc.appendChild(rootElement);

      for (int i = 0; i < Contacts.size(); i++) {

        Contact currentContact = Contacts.elementAt(i);

        // Contact elements
        Element contact = doc.createElement("Contact");
        rootElement.appendChild(contact);

        // set attribute to contact element
        Attr attr = doc.createAttribute("id");
        attr.setValue(String.valueOf(i));
        contact.setAttributeNode(attr);

        // shorten way
        // contact.setAttribute("id", "1");

        // name elements
        Element name = doc.createElement("Name");
        name.appendChild(doc.createTextNode(currentContact.returnName()));
        contact.appendChild(name);

        // postalAddress elements
        Element postalAddress = doc.createElement("PostalAddress");
        postalAddress.appendChild(doc.createTextNode
            (currentContact.returnPostal()));
        contact.appendChild(postalAddress);

        // phoneNumber elements
        Element phoneNumber = doc.createElement("PhoneNumber");
        phoneNumber.appendChild(doc.createTextNode(currentContact.
            returnPhone()));
        contact.appendChild(phoneNumber);

        // email elements
        Element email = doc.createElement("EmailAddress");
        email.appendChild(doc.createTextNode(currentContact.returnEmail()));
        contact.appendChild(email);

        //Note elemets
        Element note = doc.createElement("Note");
        note.appendChild(doc.createTextNode(currentContact.returnNote()));
        contact.appendChild(note);
      }

      // write the content into xml file
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File(Path));

      // Output to console for testing
      // StreamResult result = new StreamResult(System.out);

      transformer.transform(source, result);

      log.log(Level.FINE, "File saved!", AddressBookName);

    } catch (ParserConfigurationException pce) {
      pce.printStackTrace();
    } catch (TransformerException tfe) {
      tfe.printStackTrace();
    }
    return 0;
  }

  /**
   * Read an address book from a local file.
   * @return 0 if successful and -1 otherwise.
   */
  public  int readFromLocalFile(String path){

    //Example of path name.
    //Path = "/Users/Greyjoy/Desktop/file.xml";

    if (this.Contacts.size() != 0){
      log.info("Address book not empty, you can only read file to an empty "
          + "address book to avoid lose data by accident.");
      return -1;
    }

    try {

      if (!new File(path).exists())
      {
        log.info("No file under the current path");
        throw new FileNotFoundException("No file under the current path");
      }

      File fXmlFile = new File(path);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(fXmlFile);

      doc.getDocumentElement().normalize();

      NodeList nList = doc.getElementsByTagName("Contact");

      for (int temp = 0; temp < nList.getLength(); temp++) {

        Node nNode = nList.item(temp);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

          Element eElement = (Element) nNode;

          String Name = eElement.getElementsByTagName("Name").item(0).
              getTextContent();
          String PostalAddress = eElement.getElementsByTagName("PostalAddress")
              .item(0).getTextContent();
          String PhoneNumber = eElement.getElementsByTagName("PhoneNumber")
              .item(0).getTextContent();
          String EmailAddress = eElement.getElementsByTagName("EmailAddress")
              .item(0).getTextContent();
          String Note = eElement.getElementsByTagName("Note").item(0)
              .getTextContent();

          Contact contactToBeAdded = new Contact.Builder(Name).
              PostalAddress(PostalAddress).
              PhoneNumber(PhoneNumber).EmailAddress(EmailAddress).Note(Note).
              build();
          this.addContact(contactToBeAdded);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }
}
