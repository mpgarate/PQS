package edu.nyu.mpgarate.pqs1;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.mpgarate.pqs1.AddressBook;
import edu.nyu.mpgarate.pqs1.Contact;
import edu.nyu.mpgarate.pqs1.ContactBuilder;

public class AddressBookTest {

  private AddressBook addressBook;

  @Before
  public void initialize() {
    addressBook = new AddressBook();
  }

  @Test
  public void createEmptyAddressBook() {
    assertEquals(addressBook.size(), 0);
  }

  @Test
  public void addAnEmptyContact() {
    ContactBuilder builder = new ContactBuilder();
    addressBook.add(builder.build());

    assertEquals(addressBook.size(), 1);
  }

  @Test
  public void removeAnEmptyContact() {
    ContactBuilder builder = new ContactBuilder();
    Contact contact = builder.build();

    addressBook.add(contact);
    assertEquals(addressBook.size(), 1);

    addressBook.remove(contact.getUniqueId());
    assertEquals(addressBook.size(), 0);
  }

  @Test
  public void findAContact() {
    ContactBuilder builder = new ContactBuilder();
    Contact contact = builder.build();
    addressBook.add(contact);

    Contact foundContact = addressBook.find(contact.getUniqueId());

    assertEquals(foundContact.getUniqueId(), contact.getUniqueId());
  }

  @Test
  public void updateAnEmptyContact() {
    Contact contact = new ContactBuilder().build();
    addressBook.add(contact);

    Contact foundContact = addressBook.find(contact.getUniqueId());

    String newName = "John Doe";
    Contact modifiedContact =
        new ContactBuilder(foundContact).withName(newName).build();

    addressBook.update(modifiedContact);
    contact = addressBook.find(modifiedContact.getUniqueId());

    assertEquals(newName, contact.getName());
  }

  @Test
  public void contactsInAlphaNumericalOrderByName() {
    addressBook.add(new ContactBuilder().withName("beth").build());
    addressBook.add(new ContactBuilder().withName(":)").build());
    addressBook.add(new ContactBuilder().withName("Zed").build());
    addressBook.add(new ContactBuilder().withName("6").build());
    addressBook.add(new ContactBuilder().withName("5").build());
    addressBook.add(new ContactBuilder().withName("50").build());
    addressBook.add(new ContactBuilder().withName("++").build());
    addressBook.add(new ContactBuilder().withName("Anna").build());

    List<Contact> contacts = addressBook.all();

    assertEquals(contacts.get(0).getName(), ":)");
    assertEquals(contacts.get(1).getName(), "++");
    assertEquals(contacts.get(2).getName(), "5");
    assertEquals(contacts.get(3).getName(), "50");
    assertEquals(contacts.get(4).getName(), "6");
    assertEquals(contacts.get(5).getName(), "Anna");
    assertEquals(contacts.get(6).getName(), "beth");
    assertEquals(contacts.get(7).getName(), "Zed");
  }
}
