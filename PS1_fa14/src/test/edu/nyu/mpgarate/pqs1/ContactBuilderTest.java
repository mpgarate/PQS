package edu.nyu.mpgarate.pqs1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.mpgarate.pqs1.Contact;
import edu.nyu.mpgarate.pqs1.ContactBuilder;

public class ContactBuilderTest {
  private ContactBuilder contactBuilder;

  @Before
  public void initialize() {
    this.contactBuilder = new ContactBuilder();
  }

  @Test
  public void buildAnEmptyContact() {
    Contact contact = contactBuilder.build();
    assertEquals(null, contact.getName());
  }

  @Test
  public void buildAContactWithName() {
    String name = "John Doe";
    Contact contact = contactBuilder.withName(name).build();
    assertEquals(name, contact.getName());
  }

  @Test
  public void buildAContactWithPostalAddress() {
    String postalAddress = "251 Mercer St, New York, NY 10012";
    Contact contact = contactBuilder.withPostalAddress(postalAddress).build();
    assertEquals(postalAddress, contact.getPostalAddress());
  }

  @Test
  public void buildAContactWithPhoneNumber() {
    String phoneNumber = "+13015559284";
    Contact contact = contactBuilder.withPhoneNumber(phoneNumber).build();
    assertEquals(phoneNumber, contact.getPhoneNumber());
  }

  @Test
  public void buildAContactWithEmailAddress() {
    String emailAddress = "example@example.com";
    Contact contact = contactBuilder.withEmailAddress(emailAddress).build();
    assertEquals(emailAddress, contact.getEmailAddress());
  }

  @Test
  public void buildAContactWithNote() {
    String note =
        "Sed ut perspiciatis unde omnis iste natus error sit voluptatem"
            + " uaccusantium doloremque laudantium, totam rem aperiam, eaque"
            + "ipsa quae ab illo inventore veritatis et quasi architecto"
            + "beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem";

    Contact contact = contactBuilder.withNote(note).build();
    assertEquals(note, contact.getNote());
  }

  @Test
  public void ignoreEmptyNote() {
    Contact contact = new ContactBuilder().withNote("").build();
    assertEquals(null, contact.getNote());
  }

  @Test
  public void ignoreEmptyName() {
    Contact contact = new ContactBuilder().withName("").build();
    assertEquals(null, contact.getName());
  }

  @Test
  public void ignoreEmptyPostalAddress() {
    Contact contact = new ContactBuilder().withPostalAddress("").build();
    assertEquals(null, contact.getPostalAddress());
  }

  @Test
  public void ignoreEmptyEmailAddress() {
    Contact contact = new ContactBuilder().withEmailAddress("").build();
    assertEquals(null, contact.getEmailAddress());
  }

  @Test
  public void ignoreEmptyPhoneNumber() {
    Contact contact = new ContactBuilder().withPhoneNumber("").build();
    assertEquals(null, contact.getPhoneNumber());
  }
}
