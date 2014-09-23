package edu.nyu.mpgarate.pqs1;

import java.util.UUID;

/**
 * A representation of an entry in an address book. It consists of a name,
 * postal address, phone number, email address, and note. An additional uniqueId
 * field allows for consistent references to a contact across sessions. This
 * class is publicly immutable such that it must be constructed and modified
 * with the ContactBuilder class.
 * 
 * When adding a new attribute to Contact, consider updating the
 * attributesContainQueryTerm method for the new attribute to be visible by
 * search.
 * 
 * @see ContactBuilder
 * @author Michael Garate
 *
 */
public final class Contact {
  private String name;
  private String postalAddress;
  private String phoneNumber;
  private String emailAddress;
  private String note;
  private UUID uniqueId;

  /**
   * Construct a new contact using attributes from a populated ContactBuilder.
   * 
   * @param builder
   *          the ContactBuilder instance used to set attribute values for this
   *          contact
   */
  Contact(ContactBuilder builder) {
    name = builder.getName();
    postalAddress = builder.getPostalAddress();
    phoneNumber = builder.getPhoneNumber();
    emailAddress = builder.getEmailAddress();
    note = builder.getNote();
    uniqueId = builder.getUniqueId();
  }

  /**
   * Get the name value for this contact.
   * 
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * 
   * Get the postalAddress value for this contact.
   * 
   * @return the postal address
   */
  public String getPostalAddress() {
    return postalAddress;
  }

  /**
   * Get the phoneNumber value for this contact.
   * 
   * @return the phone number
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Get the emailAddress value for this contact.
   * 
   * @return the email address
   */
  public String getEmailAddress() {
    return emailAddress;
  }

  /**
   * Get the note value for this contact.
   * 
   * @return the note
   */
  public String getNote() {
    return note;
  }

  /**
   * Get the uniqueId of this contact.
   * 
   * @return the unique id
   */
  public UUID getUniqueId() {
    return uniqueId;
  }

  /**
   * Empty constructor required by Jackson library for JSON deserialization.
   */
  private Contact() {

  }

  /**
   * Private setter for name attribute required by Jackson library for JSON
   * deserialization.
   * 
   * @param name
   *          the name value to set
   */
  private void setName(String name) {
    this.name = name;
  }

  /**
   * Private setter for postalAddress attribute required by Jackson library for
   * JSON deserialization.
   * 
   * @param postalAddress
   *          the postalAddress value to set
   */
  private void setPostalAddress(String postalAddress) {
    this.postalAddress = postalAddress;
  }

  /**
   * Private setter for phoneNumber attribute required by Jackson library for
   * JSON deserialization.
   * 
   * @param phoneNumber
   *          the phoneNumber value to set
   */
  private void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Private setter for emailAddress attribute required by Jackson library for
   * JSON deserialization.
   * 
   * @param emailAddress
   *          the emailAddress value to set
   */
  private void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  /**
   * Private setter for note attribute required by Jackson library for JSON
   * deserialization.
   * 
   * @param note
   *          the note value to set.
   */
  private void setNote(String note) {
    this.note = note;
  }

  /**
   * Returns a String representation of this contact.
   * 
   * @return a String representation of this contact.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("{");
    sb.append("name=" + name + ", ");
    sb.append("phoneNumber=" + phoneNumber + ", ");
    sb.append("postalAddress=" + postalAddress + ", ");
    sb.append("emailAddress=" + emailAddress + ", ");
    sb.append("note=" + note);
    sb.append("}");

    return sb.toString();
  }

  /**
   * Returns true if an attribute if this contact contains the full specified
   * query. For query that is null, empty, or all whitespace, true is returned.
   * When adding a new attribute to Contact, update this method if it should
   * visible by search.
   * 
   * @param term
   * @return true if term is present in contact attributes, false otherwise
   */

  boolean attributesContainQueryTerm(String term) {
    if (Util.isNullOrEmptyOrWhitespace(term)) {
      return true;
    }

    return (null != name && name.contains(term))
        || (null != postalAddress && postalAddress.contains(term))
        || (null != note && note.contains(term))
        || (null != emailAddress && emailAddress.contains(term))
        || (null != phoneNumber && phoneNumber.contains(term));
  }

}
