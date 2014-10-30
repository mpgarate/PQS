package nyu.edu.pqs143;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class AddressBookTest {
  @Test
  public void testCreateEmptyAddressBook() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    assertEquals(0, addressBook.getEntries().size());
  }

  @Test
  public void testAddEntry_minimal() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    addressBook.addEntry(new Entry.Builder("john").build());
    assertEquals("john", addressBook.getEntries().get(0).getName());
  }

  @Test
  public void testAddEntry_withAllFields() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    Entry entry =
        new Entry.Builder("John").address("123 mercer st.")
            .email("john@example.com").note("met at school")
            .phone("+12025552351").build();

    addressBook.addEntry(entry);

    assertEquals(entry, addressBook.getEntries().get(0));
  }

  @Test
  public void testAddEntry_fromString() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    addressBook.addEntry("John,null,null,null,null");
    assertEquals("John", addressBook.getEntries().get(0).getName());
  }

  @Test
  public void testAddEntry_fromStringWithAllFields() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();

    // The order of the comma-separated fields is not documented
    // Had to look at code to determine correct order
    String entryString =
        "John,123 mercer st.,+12025552351,john@example.com,met at school";
    addressBook.addEntry(entryString);

    Entry entry = addressBook.getEntries().get(0);

    assertEquals("John", entry.getName());
    assertEquals("123 mercer st.", entry.getAddress());
    assertEquals("+12025552351", entry.getPhone());
    assertEquals("john@example.com", entry.getEmail());
    assertEquals("met at school", entry.getNote());
  }

  @Test
  public void testRemoveEntry_sameObject() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    Entry entry = new Entry.Builder("john").build();
    addressBook.addEntry(entry);
    addressBook.removeEntry(entry);
    assertEquals(0, addressBook.getEntries().size());
  }

  /**
   * AddressBook should keep its internal data structures private so that
   * clients cannot corrupt the data.
   */
  @Test
  public void _failingTestAddEntry_encapsulation() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    addressBook.addEntry(new Entry.Builder("john").build());
    List<Entry> entries = addressBook.getEntries();
    entries.remove(0);
    assertEquals("john", addressBook.getEntries().get(0).getName());
  }

  @Test
  public void testSaveToFile_withAllFields() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    Entry entry =
        new Entry.Builder("John").address("123 mercer st.")
            .email("john@example.com").note("met at school")
            .phone("+12025552351").build();

    addressBook.addEntry(entry);

    addressBook.saveToFile("foo");
    addressBook = AddressBook.createEmptyAddressBook();
    addressBook.readFromFile("foo", ",");

    Entry reloadedEntry = addressBook.getEntries().get(0);
    assertEquals("John", entry.getName());
    assertEquals("123 mercer st.", entry.getAddress());
    assertEquals("john@example.com", entry.getEmail());
    assertEquals("met at school", entry.getNote());
    assertEquals("+12025552351", entry.getPhone());
  }

  /**
   * The AddressBbook should throw an IOException but throws a
   * NullPointerException
   */

  @Test(expected = IOException.class)
  public void _failingTestSaveToFile_withRootPath() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    addressBook.saveToFile("/");
  }

  /**
   * The AddressBook should throw an exception when reading or writing to an
   * invalid path. In this test, the AddressBook fails silently.
   */
  @Test
  public void _failingTestSaveToFile_withInvalidCharacterPath() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    addressBook.addEntry(new Entry.Builder("John").build());
    addressBook.saveToFile("*");

    AddressBook newAddressBook = AddressBook.createEmptyAddressBook();
    newAddressBook.readFromFile("*", ",");

    assertEquals("John", newAddressBook.getEntries().get(0).getName());
  }

  /**
   * This test fails because the AddressBook does not support having commas in
   * Entry fields.
   */

  @Test
  public void _failingTestSaveToFile_withCommasInEntryFields() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    Entry entry =
        new Entry.Builder("Smith, John").address("404 mercer st, NY, NY 10012")
            .email("john@example.com")
            .note("I met John, one of my best friends, at school.").build();

    addressBook.addEntry(entry);

    addressBook.saveToFile("foo");
    addressBook = AddressBook.createEmptyAddressBook();
    addressBook.readFromFile("foo", ",");

    Entry reloadedEntry = addressBook.getEntries().get(0);
    assertEquals("Smith, John", reloadedEntry.getName());
  }

  @Test
  public void testSearchEntryByName_simple() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    addressBook.addEntry(new Entry.Builder("John").build());

    Entry foundEntry = addressBook.searchEntryByName("John").get(0);

    assertEquals("John", foundEntry.getName());
  }

  @Test
  public void testSearchEntry_withExactNameString() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    addressBook.addEntry(new Entry.Builder("Phil").build());
    addressBook.addEntry(new Entry.Builder("John").build());
    addressBook.addEntry(new Entry.Builder("Jane").build());
    addressBook.addEntry(new Entry.Builder("Mary").build());
    addressBook.addEntry(new Entry.Builder("Phil").build());

    Entry foundEntry = addressBook.searchEntry("John").get(0);

    assertEquals("John", foundEntry.getName());
  }

  @Test
  public void testSearchEntryByName_withTwoEntries() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    addressBook.addEntry(new Entry.Builder("John").build());
    addressBook.addEntry(new Entry.Builder("Jane").build());

    Entry foundEntry = addressBook.searchEntryByName("John").get(0);

    assertEquals("John", foundEntry.getName());
  }

  @Test
  public void testSearchEntryByName_withPartialMatch() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    addressBook.addEntry(new Entry.Builder("John").build());
    addressBook.addEntry(new Entry.Builder("Jane").build());

    Entry foundEntry = addressBook.searchEntryByName("Jo").get(0);

    assertEquals("John", foundEntry.getName());
  }

  @Test
  public void testSearchEntryByName_withSpecialCharacters() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    addressBook.addEntry(new Entry.Builder("John").build());
    addressBook.addEntry(new Entry.Builder("~!@#$%^&*()_+{}|:\"<>`-=[]\\;',./")
        .build());
    addressBook.addEntry(new Entry.Builder("Steve").build());

    Entry foundSpecial =
        addressBook.searchEntryByName("~!@#$%^&*()_+{}|:\"<>`-=[]\\;',./").get(
            0);

    Entry foundJohn = addressBook.searchEntryByName("John").get(0);

    assertEquals("~!@#$%^&*()_+{}|:\"<>`-=[]\\;',./", foundSpecial.getName());
    assertEquals("John", foundJohn.getName());
  }

  @Test
  public void testSearchEntryByAddress_simple() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    addressBook.addEntry(new Entry.Builder("John").address("123 mercer st.")
        .build());

    addressBook.addEntry(new Entry.Builder("John").address("555 mercer st.")
        .build());

    Entry found123Mercer =
        addressBook.searchEntryByAddress("123 mercer").get(0);

    assertEquals("123 mercer st.", found123Mercer.getAddress());
  }

  @Test
  public void testSearchEntryByPhone_simple() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    addressBook.addEntry(new Entry.Builder("John").phone("44446789").build());

    addressBook.addEntry(new Entry.Builder("John").phone("+15555555555")
        .build());

    Entry foundEntry = addressBook.searchEntryByPhone("5").get(0);

    assertEquals("+15555555555", foundEntry.getPhone());
  }

  @Test
  public void testSearchEntryByEmail_simple() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    addressBook.addEntry(new Entry.Builder("John").email("john@example.com")
        .build());

    addressBook.addEntry(new Entry.Builder("Phil").email("phil@example.com")
        .build());

    Entry foundEntry = addressBook.searchEntryByEmail("phil@").get(0);

    assertEquals("phil@example.com", foundEntry.getEmail());
  }

  @Test
  public void testSearchEntryByNote_simple() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    addressBook.addEntry(new Entry.Builder("John").note("from example org")
        .build());

    addressBook.addEntry(new Entry.Builder("Phil").note("from other org")
        .build());

    Entry foundEntry = addressBook.searchEntryByNote("example org").get(0);

    assertEquals("from example org", foundEntry.getNote());
  }

  /**
   * This test fails because the search algorithm calls
   * entry.toString().contains(term), and the toString() implementation joins
   * all fields with commas.
   */
  @Test
  public void testSearchEntryByName_forNonMatchingStringWithComma() {
    AddressBook addressBook = AddressBook.createEmptyAddressBook();
    addressBook.addEntry(new Entry.Builder("John").build());
    addressBook.addEntry(new Entry.Builder("Steve").build());
    addressBook.addEntry(new Entry.Builder("Bart").build());
    addressBook.addEntry(new Entry.Builder("Matthew Jones").build());

    List<Entry> results = addressBook.searchEntry(",,,,");
    assertEquals(0, results.size());
  }
}
