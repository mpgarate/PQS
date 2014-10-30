package nyu.edu.pqs143;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents an address book.
 * An address book has an arrayList of entry. 
 * An empty address book can be created.
 * An address book can add and remove an entry.
 * An address book can search for entries by (one of) the properties.
 * An address book can be read from a file and saved to a file.
 */
public class AddressBook {

	private ArrayList<Entry> entries;

	private AddressBook() {
		entries = new ArrayList<Entry>();
	}

  /**
   * Gets the entries of the address book.
   * 
   * @return the entries of the address book
   */
	public ArrayList<Entry> getEntries() {
		return entries;
	}

	/**
	 * Creates and returns an empty address book.
	 * 
	 * @return an empty address book
	 */
	public static AddressBook createEmptyAddressBook() {
		return new AddressBook();
	}

	/**
	 * Adds an entry converted from its string representation.
	 * By default, the entry members are split by comma(","),
	 * which is commonly used in csv files.
	 * 
	 * @param s    the String representation of the entry
	 * @return     true if the entry is added successfully
	 */
	public boolean addEntry(String s) {
		return addEntry(s, ",");
	}

  /**
   * Adds an entry converted from its string representation.
   * The entry members are split the specified delimiter.
   * 
   * @param s            the string representation of the entry
   * @param delimiter    the string delimiter
   * @return             true if the entry is added successfully
   */
	public boolean addEntry(String s, String delimiter) {
		return addEntry(Entry.toEntry(s, delimiter));
	}

	/**
	 * Adds an entry to the entries of the address book.
	 * 
	 * @param e    the entry
	 * @return     true if the entry is added successfully
	 */
	public boolean addEntry(Entry e) {
		return entries.add(e);
	}

	/**
	 * Removes an entry from the entries of the address book.
	 * 
	 * @param e    the entry
	 * @return     true if the entry is removed successfully
	 * @throws NullPointerException    if the entry is null
	 */
	public boolean removeEntry(Entry e) throws NullPointerException {
		return entries.remove(e);
	}

	/**
	 * Searches and returns the entries 
	 * whose properties containing the keyword.
	 * 
	 * @param s    the keyword
	 * @return     the entries containing the keyword
	 */
	public ArrayList<Entry> searchEntry(String s) {
		ArrayList<Entry> searchedEntries = new ArrayList<Entry>();
		for (Entry e : entries) {
			if (e.toString().contains(s)) {
				searchedEntries.add(e);
			}
		}
		return searchedEntries;
	}

	/**
	 * Searches and returns the entries
	 * whose name containing the keyword.
	 * 
	 * @param s    the keyword
	 * @return     the entries whose name containing the keyword
	 */
	public ArrayList<Entry> searchEntryByName(String s) {
		ArrayList<Entry> searchedEntriesByName = new ArrayList<Entry>();
		for (Entry e : entries) {
			if (e.getName().contains(s)) {
				searchedEntriesByName.add(e);
			}
		}
		return searchedEntriesByName;
	}

	 /**
   * Searches and returns the entries
   * whose address containing the keyword.
   * 
   * @param s    the keyword
   * @return     the entries whose address containing the keyword
   */
	public ArrayList<Entry> searchEntryByAddress(String s) {
		ArrayList<Entry> searchedEntriesByAddress = new ArrayList<Entry>();
		for (Entry e : entries) {
			if (e.getAddress().contains(s)) {
				searchedEntriesByAddress.add(e);
			}
		}
		return searchedEntriesByAddress;
	}

	 /**
   * Searches and returns the entries
   * whose phone containing the keyword.
   * 
   * @param s    the keyword
   * @return     the entries whose phone containing the keyword
   */
	public ArrayList<Entry> searchEntryByPhone(String s) {
		ArrayList<Entry> searchedEntriesByPhone = new ArrayList<Entry>();
		for (Entry e : entries) {
			if (e.getPhone().contains(s)) {
				searchedEntriesByPhone.add(e);
			}
		}
		return searchedEntriesByPhone;
	}

	 /**
   * Searches and returns the entries
   * whose email containing the keyword.
   * 
   * @param s    the keyword
   * @return     the entries whose email containing the keyword
   */
	public ArrayList<Entry> searchEntryByEmail(String s) {
		ArrayList<Entry> searchedEntriesByEmail = new ArrayList<Entry>();
		for (Entry e : entries) {
			if (e.getEmail().contains(s)) {
				searchedEntriesByEmail.add(e);
			}
		}
		return searchedEntriesByEmail;
	}

	 /**
   * Searches and returns the entries
   * whose note containing the keyword.
   * 
   * @param s    the keyword
   * @return     the entries whose note containing the keyword
   */
	public ArrayList<Entry> searchEntryByNote(String s) {
		ArrayList<Entry> searchedEntriesByNote = new ArrayList<Entry>();
		for (Entry e : entries) {
			if (e.getNote().contains(s)) {
				searchedEntriesByNote.add(e);
			}
		}
		return searchedEntriesByNote;
	}

	/**
	 * Saves the address book to a file.
	 * Each entry takes a line, and the members are separated 
	 * by comma(","), which is commonly used in csv files.
	 * 
	 * @param path    the file path
	 */
	public void saveToFile(String path) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(path));
			for (Entry e : entries) {
				writer.write(e.toString() + "\n");
			}
		} catch (IOException writeToFileErr) {
		  try {
        writer.flush();
      } catch (IOException writerFlushErr) {
        writerFlushErr.printStackTrace();
      }
		  writeToFileErr.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException invalidWriterErr) {
					invalidWriterErr.printStackTrace();
				}
			}
		}
	}

	/**
	 * Reads the entries from a file.
	 * Each entry takes a line, and the members are separated
	 * by the specified delimiter.
	 * Each entry needs to consist of Entry.MEMBER_NUM (currently 5) members,
	 * which follow the order as:
	 * 1 Name (Required, cannot be empty)
	 * 2 Address
	 * 3 Phone
	 * 4 Email
	 * 5 Note
	 * An invalid entry will not be saved into the address book.
	 * The existed entries of the address book are NOT overwritten.
	 * 
	 * @param path         the file path
	 * @param delimiter    the string delimiter
	 * @return             the number of entries read and saved successfully
	 */
	public int readFromFile(String path, String delimiter) {
		int successReadIn = 0;
		BufferedReader reader = null;
		ArrayList<Entry> tempEntries = new ArrayList<Entry>();
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = null;
			while ((line = reader.readLine()) != null) {
				Entry e = Entry.toEntry(line, delimiter);
				if (e != null) {
					tempEntries.add(e);
					successReadIn++;
				}
			}
		} catch (IOException readFromFileErr) {
		  successReadIn = 0;
			readFromFileErr.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException invalidReaderErr) {
					invalidReaderErr.printStackTrace();
				}
			}
		}
		entries.addAll(tempEntries);
		return successReadIn;
	}
}
