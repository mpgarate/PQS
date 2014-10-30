package nyu.edu.pqs143;

/**
 * Represents an entry in the address book.
 * An entry consists of MEMBER_NUM (currently 5) string members, 
 * which are name (required), address (optional), phone (optional),
 * email (optional) and note (optional). 
 * An entry can be built with the static builder class.
 * An entry can be converted to a string representation.
 */
public class Entry {

  public static final int MEMBER_NUM = 5;
  private String name;
  private String address;
  private String phone;
  private String email;
  private String note;

  /**
   * Represents the static builder for class Entry. The builder has the fields
   * of name, address, phone, email and note.
   */
  public static class Builder {
    private String name;
    private String address = "";
    private String phone = "";
    private String email = "";
    private String note = "";

    public Builder(String name) {
      this.name = name;
    }

    public Builder address(String address) {
      this.address = address;
      return this;
    }

    public Builder phone(String phone) {
      this.phone = phone;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder note(String note) {
      this.note = note;
      return this;
    }

    public Entry build() {
      return new Entry(this);
    }
  }

  private Entry(Builder builder) {
    name = builder.name;
    address = builder.address;
    phone = builder.phone;
    email = builder.email;
    note = builder.note;
  }

  /**
   * Returns the entry converted from the string.
   * By default, the members are split by comma(","),
   * which is commonly used in csv files. 
   * Any member except for Name can be empty.
   * 
   * @param s    the string representation of an entry
   * @return     the entry
   */
  public static Entry toEntry(String s) {
    return toEntry(s, ",");
  }

  /**
   * Returns the entry converted from the string.
   * The members are split by the specified delimiter. 
   * Any member except for Name can be empty.
   * 
   * @param s            the string representation of an entry
   * @param delimiter    the string delimiter
   * @return             the entry
   */
  public static Entry toEntry(String s, String delimiter) {
    String[] temp = s.split(delimiter);
    String[] members = new String[temp.length];
    for (int i = 0; i < temp.length; i++) {
      members[i] = temp[i].trim();
    }
    if (members.length == MEMBER_NUM && members[0] != null) {
      Entry e = new Entry.Builder(members[0]).address(members[1])
          .phone(members[2]).email(members[3]).note(members[4]).build();
      return e;
    } else {
      return null;
    }
  }

  /**
   * Returns the string representation of the entry. The string 
   * consists of NUMBER_NUM (currently 5) string members 
   * whose format is "Name,Address,Phone,Email,Note". 
   * Any member except for Name can be empty.
   * 
   * @return the string representation of the entry
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(name + "," + address + "," + phone + "," + email + "," + note);
    return sb.toString();
  }

  /**
   * Sets the Name.
   * 
   * @param name    the string of Name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the Address.
   * 
   * @param address    the string of Address
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Sets the Phone.
   * 
   * @param phone    the string of Phone
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * Sets the Email.
   * 
   * @param email    the string of Email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Sets the Note.
   * 
   * @param note    the string of Note
   */
  public void setNote(String note) {
    this.note = note;
  }

  /**
   * Gets the Name of the entry.
   * 
   * @return the Name of the entry
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the Address of the entry.
   * 
   * @return the Address of the entry
   */
  public String getAddress() {
    return address;
  }

  /**
   * Gets the Phone of the entry.
   * 
   * @return the Phone of the entry
   */
  public String getPhone() {
    return phone;
  }

  /**
   * Gets the Email of the entry.
   * 
   * @return the Email of the entry
   */
  public String getEmail() {
    return email;
  }

  /**
   * Gets the Note of the entry.
   * 
   * @return the Note of the entry
   */
  public String getNote() {
    return note;
  }

}
