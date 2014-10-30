package nyu.edu.al3565;
import java.util.logging.Logger;

/**
 * This test class is to see what will result as a consequence of doing some
 *  bizzare things.
 * Created by Greyjoy on 9/23/14.
 */
public class Test {
  private static final Logger log = Logger.getLogger( Test.class.getName() );
  private Contact Li = new Contact.Builder("Li").EmailAddress("al3565").build();
  private Contact Wang = new Contact.Builder("Wang").Note("Beauty").build();
  private AddressBook testBook = new AddressBook("testBook");

  public Test(){
    //Required function
    //CreateEmptyAddressBook();
    //AddAnEntry();
    //SearchForAnEntry();
    //writeToLocalFile();
    //readFromLocalFile();
    
    //My Test
    //DuplicateAddressBookName();
    //NullContact();
    //CreateNullAddressBook();
    //IntPhoneNum();
  }

  private void AddAnEntry(){
    testBook.addContact(Li);
    log.info(testBook.toString());
  }

  private void SearchForAnEntry(){
    testBook.addContact(Li);
    testBook.addContact(Wang);
    log.info(testBook.search("a").toString());

  }

  private void DuplicateAddressBookName() {
    AddressBook iCloud = new AddressBook("iCloud");
    AddressBook myMac = new AddressBook("iCloud");
    iCloud.addContact(Li);
    myMac.addContact(Wang);
    log.info(String.valueOf(iCloud));
    log.info(String.valueOf(myMac));
  }

  private void CreateEmptyAddressBook() {
    AddressBook iCloud = new AddressBook("iCloud");
    log.info(String.valueOf(iCloud));
  }

  private void NullContact(){
    Contact Yang = new Contact.Builder(null).PhoneNumber("1414").build();
    testBook.addContact(Yang);
    //        log.info(String.valueOf(testBook));
    //        log.info(String.valueOf(testBook.search(null)));
  }

  private void SearchWithNull(){
    testBook.search(null);
  }

  private void CreateNullAddressBook(){
    AddressBook nullBook = new AddressBook(null);
  }

  private void IntPhoneNum(){
    Contact a = new Contact.Builder("A").PhoneNumber(32423).build();
    log.info(a.toString());
  }

  private void writeToLocalFile(){
    testBook.addContact(Li);
    testBook.addContact(Wang);
    testBook.writeToLocalFile("/Users/Greyjoy/Desktop/file.xml");
  }

  private void readFromLocalFile(){
    testBook.readFromLocalFile("/Users/Greyjoy/Desktop/file.xml");
    log.info(testBook.toString());
  }
}
