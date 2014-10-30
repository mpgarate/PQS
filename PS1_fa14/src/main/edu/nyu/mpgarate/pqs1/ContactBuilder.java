package edu.nyu.mpgarate.pqs1;

import java.util.UUID;

/**
 * Using the Builder Pattern, construct or update a Contact. Setter methods
 * begin with the word 'with' and return this ContactBuilder instance.
 * Validation is performed inside the setters to prevent values from being set
 * to only whitespace. No other validation is performed in order to allow the
 * user flexibility with inputs.
 * 
 * @author Michael Garate
 *
 */
public final class ContactBuilder implements Builder<Contact> {

  private String name;
  private String postalAddress;
  private String emailAddress;
  private String phoneNumber;
  private String note;
  private UUID uniqueId;

  /**
   * Constructs a builder instance for creating a new Contact with a freshly
   * generated uniqueId.
   */
  public ContactBuilder() {
    uniqueId = UUID.randomUUID();
  }

  /**
   * Constructs a builder with the values of a Contact instance so that it can
   * be updated and rebuilt. This preserves the uniqueId of the original
   * contact.
   * 
   * @param contact
   *          the contact to be updated
   */
  public ContactBuilder(Contact contact) {
    name = contact.getName();
    postalAddress = contact.getPostalAddress();
    phoneNumber = contact.getPhoneNumber();
    note = contact.getNote();
    uniqueId = contact.getUniqueId();
  }

  /**
   * Sets a name for the contact being built.
   * 
   * @param name
   *          the name value to be set.
   * @return the ContactBuilder instance.
   */
  public ContactBuilder withName(String name) {
    if (!Util.isNullOrEmptyOrWhitespace(name)) {
      this.name = name;
    }

    return this;
  }

  /**
   * Sets a postal address for the contact being built.
   * 
   * @param postalAddress
   *          the postal address value to be set.
   * @return the ContactBuilder instance.
   */
  public ContactBuilder withPostalAddress(String postalAddress) {
    if (!Util.isNullOrEmptyOrWhitespace(postalAddress)) {
      this.postalAddress = postalAddress;
    }

    return this;
  }

  /**
   * Sets a email address for the contact being built.
   * 
   * @param emailAddress
   *          the email address value to be set.
   * @return the ContactBuilder instance.
   */
  public ContactBuilder withEmailAddress(String emailAddress) {
    if (!Util.isNullOrEmptyOrWhitespace(emailAddress)) {
      this.emailAddress = emailAddress;
    }

    return this;
  }

  /**
   * Sets a phone number for the contact being built.
   * 
   * @param phoneNumber
   *          the phone number value to be set.
   * @return the ContactBuilder instance.
   */
  public ContactBuilder withPhoneNumber(String phoneNumber) {
    if (!Util.isNullOrEmptyOrWhitespace(phoneNumber)) {
      this.phoneNumber = phoneNumber;
    }

    return this;
  }

  /**
   * Sets a note for the contact being built.
   * 
   * @param note
   *          the note value to be set.
   * @return the ContactBuilder instance.
   */
  public ContactBuilder withNote(String note) {
    if (!Util.isNullOrEmptyOrWhitespace(note)) {
      this.note = note;
    }

    return this;
  }

  /**
   * Get the name value for this builder.
   * 
   * @return the name value
   */
  String getName() {
    return name;
  }

  /**
   * Get the postalAddress value for this builder.
   * 
   * @return the postalAddress
   */
  String getPostalAddress() {
    return postalAddress;
  }

  /**
   * Get the emailAddress value for this builder.
   * 
   * @return the emailAddress value
   */
  String getEmailAddress() {
    return emailAddress;
  }

  /**
   * Get the phoneNumber value for this builder.
   * 
   * @return the phoneNumber value
   */
  String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Get the note value for this builder.
   * 
   * @return the note value
   */
  String getNote() {
    return note;
  }

  /**
   * Get the uniqueId value for this builder.
   * 
   * @return the uniqueId value
   */
  UUID getUniqueId() {
    return uniqueId;
  }

  /**
   * Construct a new contact using this ContactBuilder instance. Values are set
   * inside the Contact class using the ContactBuilder getters.
   */
  @Override
  public Contact build() {
    return new Contact(this);
  }
}
