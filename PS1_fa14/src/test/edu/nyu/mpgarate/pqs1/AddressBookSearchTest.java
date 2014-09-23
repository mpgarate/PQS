package edu.nyu.mpgarate.pqs1;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.mpgarate.pqs1.AddressBook;
import edu.nyu.mpgarate.pqs1.Contact;
import edu.nyu.mpgarate.pqs1.ContactBuilder;

public class AddressBookSearchTest {

  private AddressBook addressBook;

  @Before
  public void initialize() {
    addressBook = new AddressBook();
  }

  @Test
  public void searchByNullQueryReturnsAll() {
    addressBook.add(new ContactBuilder().build());
    addressBook.add(new ContactBuilder().build());
    addressBook.add(new ContactBuilder().build());
    addressBook.add(new ContactBuilder().build());
    addressBook.add(new ContactBuilder().build());

    List<Contact> results = addressBook.search(null);

    assertEquals(5, addressBook.size());
    assertEquals(5, results.size());
  }

  @Test
  public void searchByEmptyQuery() {
    addressBook.add(new ContactBuilder().build());
    List<Contact> results = addressBook.search("");

    assertEquals(1, results.size());
  }

  @Test
  public void searchByName() {
    String name = "John Doe";

    Contact contact = new ContactBuilder().withName(name).build();
    addressBook.add(contact);
    addressBook.add(new ContactBuilder().withName("Jane Doe").build());
    addressBook.add(new ContactBuilder().withName("Jeff Doe").build());

    List<Contact> results = addressBook.search(name);
    Contact foundContact = results.get(0);

    assertEquals(1, results.size());
    assertEquals(contact, foundContact);
  }

  @Test
  public void searchByNameMatchingMultipleContacts() {
    String name = "John Doe";

    Contact contact = new ContactBuilder().withName(name).build();
    addressBook.add(contact);
    addressBook.add(new ContactBuilder().withName("Jane Doe").build());
    addressBook.add(new ContactBuilder().withName("Jeff Doe").build());

    List<Contact> results = addressBook.search("Doe");

    assertEquals(3, results.size());
  }

  @Test
  public void searchByPostalAddress() {
    String postalAddress = "251 Mercer St, New York, NY 10012";
    String postalAddressQuery = "251 Mercer St";

    Contact contact =
        new ContactBuilder().withPostalAddress(postalAddress).build();
    addressBook.add(contact);
    addressBook.add(new ContactBuilder().withPostalAddress(
        "206 Mercer St, New York, NY 10012").build());
    addressBook.add(new ContactBuilder().withPostalAddress(
        "150 Mercer St, New York, NY 10012").build());

    List<Contact> results = addressBook.search(postalAddressQuery);
    Contact foundContact = results.get(0);

    assertEquals(1, results.size());
    assertEquals(contact, foundContact);
  }

  @Test
  public void searchByPhoneNumber() {
    String phoneNumber = "+13015559185";
    String phoneNumberQuery = "3015559185";

    Contact contact = new ContactBuilder().withPhoneNumber(phoneNumber).build();

    addressBook.add(contact);

    List<Contact> results = addressBook.search(phoneNumberQuery);

    Contact foundContact = results.get(0);

    assertEquals(contact, foundContact);
  }

  @Test
  public void searchByEmailAddress() {
    String emailAddress = "johndoe@example.com";
    String emailAddressQuery = "johndoe example";

    Contact contact =
        new ContactBuilder().withEmailAddress(emailAddress).build();

    addressBook.add(contact);

    List<Contact> results = addressBook.search(emailAddressQuery);

    Contact foundContact = results.get(0);

    assertEquals(contact, foundContact);
  }

  @Test
  public void searchByNote() {
    String note = "lorem ipsum dolor sit amet";
    String noteQuery = "ipsum sit";

    Contact contact = new ContactBuilder().withNote(note).build();

    addressBook.add(contact);

    List<Contact> results = addressBook.search(noteQuery);

    Contact foundContact = results.get(0);

    assertEquals(contact, foundContact);
  }
}
