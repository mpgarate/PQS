package edu.nyu.mpgarate.pqs1;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import edu.nyu.mpgarate.pqs1.AddressBook;
import edu.nyu.mpgarate.pqs1.Contact;
import edu.nyu.mpgarate.pqs1.ContactBuilder;

public class AddressBookSerializationTest {

  @Test
  public void writeAndReadAddressBookViaJson() throws IOException {
    AddressBook originalAddressBook = new AddressBook();

    Contact contact1 =
        new ContactBuilder().withName("John Doe")
            .withPostalAddress("310 3rd Avenue NY, NY")
            .withEmailAddress("johndoe@example.com")
            .withNote("lorem ipsum dolor sit amet")
            .withPhoneNumber("2405555123").build();

    Contact contact2 = new ContactBuilder().withName("Andrew Smith").build();
    Contact contact3 =
        new ContactBuilder().withName("Beth Smith")
            .withPostalAddress("190 Mercer St").build();

    originalAddressBook.add(contact1);
    originalAddressBook.add(contact2);
    originalAddressBook.add(contact3);

    String targetFilePath =
        new File("src/test/resources/addressbook.json").getAbsolutePath();
    File targetFile = new File(targetFilePath);

    originalAddressBook.writeTo(targetFile);

    AddressBook newAddressBook = new AddressBook();

    newAddressBook.readFrom(targetFile);

    Contact newContact1 = newAddressBook.search("John Doe").get(0);
    Contact newContact2 = newAddressBook.search("Andrew Smith").get(0);
    Contact newContact3 = newAddressBook.search("Beth Smith").get(0);

    assertEquals(contact1.getUniqueId(), newContact1.getUniqueId());
    assertEquals(contact2.getName(), newContact2.getName());
    assertEquals(contact3.getPostalAddress(), newContact3.getPostalAddress());
  }
}
