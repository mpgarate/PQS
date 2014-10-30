package nyu.edu.al3565;
import com.sun.istack.internal.NotNull;

/**
 * This is a typical person in your address book.
 * <p>
 *     You do need to use build to create a new contact since the construct 
 *     method for contact is set private. A name for a contact is required. 
 *     Everything else are optional. However, if you don't specify the optional
 *     parameters. They will be empty string as default.Since my phone address
 *     book basically allow me to enter anything in any field. I shall put 
 *     little restriction on each attribute.
 * </p>
 * Created by Greyjoy on 9/21/14.
 */
public class Contact {

  private String Name;
  private String PostalAddress;
  private String PhoneNumber;
  private String EmailAddress;
  private String Note;

  public static class Builder {
    //Required parameters
    private String Name = "";    
    //Optional parameters
    private String PostalAddress = "";
    private String PhoneNumber = "";
    private String EmailAddress = "";
    private String Note = "";

    /**
     * Construct a new contact using builder pattern.
     * @param Name the name for this contact, cannot be null.
     * @throws IllegalArgumentException
     */
    public Builder(@NotNull String Name) throws IllegalArgumentException {
      
      if (Name == null){
        throw new IllegalArgumentException();
      }
      this.Name = Name;
    }

    /**
     * Set the postal address
     * @param val the postal address
     * @return the contact
     */
    public Builder PostalAddress(String val) {
      PostalAddress = val;
      return this;
    }

    /**
     * Set the phone number (String parameter)
     * @param val the phone number, with String value
     * @return the contact
     */
    public Builder PhoneNumber(String val) {
      PhoneNumber = val;
      return this;
    }

    /**
     * Set the phone number (long int parameter)
     * @param val convert the long int phone number to string.
     * @return the contact
     */
    public Builder PhoneNumber(long val) {
      PhoneNumber = String.valueOf(val);
      return this;
    }

    /**
     * Set email address
     * @param val email address
     * @return email address
     */
    public Builder EmailAddress(String val) {
      EmailAddress = val;
      return this;
    }

    /**
     * Set note
     * @param val content of note
     * @return the contact
     */
    public Builder Note(String val) {
      Note = val;
      return this;
    }

    /**
     * Build the contact
     * @return the contact
     */
    public Contact build() {
      return new Contact(this);
    }
  }

  /**
   * Override to string.
   * @return all the information about this contact
   */
  @Override
  public String toString() {
    return String.format("Name: %s\nPhone Number: %s\nPostal Address: %s\n"
        + "Email Address: %s\nNote: %s\n",
        Name, PhoneNumber, PostalAddress, EmailAddress, Note);
  }

  private Contact(Builder builder) {
    Name = builder.Name;
    PhoneNumber = builder.PhoneNumber;
    PostalAddress = builder.PostalAddress;
    EmailAddress = builder.EmailAddress;
    Note = builder.Note;
  }

  /**
   * Return the name of this contact
   * @return name
   */
  public String returnName() {
    return this.Name;
  }

  /**
   * Return the phone number of this contact
   * @return phone number
   */
  public String returnPhone() {
    return this.PhoneNumber;
  }

  /**
   * Return the postal address of this contact
   * @return postal address
   */
  public String returnPostal() {
    return this.PostalAddress;
  }

  /**
   * Return the email address of this contact
   * @return email address
   */
  public String returnEmail() {
    return this.EmailAddress;
  }

  /**
   * Return the content of the note of this contact
   * @return The string form of the note field of this contact
   */
  public String returnNote() {
    return this.Note;
  }

  /**
   * Check if this contact contains the key word that the user searches in any 
   * of its attribute.
   * @param keyWord the key word that user is interested in searching
   * @return true if contains the keyword, no otherwise
   */
  public boolean containsKeyWord(String keyWord) {
    if (this.Name.contains(keyWord) || this.PhoneNumber.contains(keyWord)
        || this.EmailAddress.contains(keyWord) || this.PostalAddress.
        contains(keyWord) || this.Note.contains(keyWord)) {
      return true;
    }
    else {
      return false;
    }
  }
}
