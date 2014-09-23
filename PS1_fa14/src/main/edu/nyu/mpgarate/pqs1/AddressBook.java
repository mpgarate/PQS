package edu.nyu.mpgarate.pqs1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Library for storing contact information. Contacts can be created, removed,
 * updated, fetched by unique id (UUID), and searched for. The entire set of
 * contacts can be written to or read from a file.
 * 
 * @author Michael Garate
 *
 */
public final class AddressBook {

  HashMap<UUID, Contact> contacts = new HashMap<UUID, Contact>();

  /**
   * Returns the number of contacts in the addressBook.
   * 
   * @return the number of contacts
   */
  public int size() {
    return contacts.size();
  }

  /**
   * Adds a contact to the addressBook.
   * 
   * @param contact
   *          the contact to add
   * @return the old contact if another with the same uniqueId was replaced.
   */
  public Contact add(Contact contact) {
    if (null == contact) {
      return null;
    }
    return contacts.put(contact.getUniqueId(), contact);
  }

  /**
   * Remove a specified contact by its unique Id.
   * 
   * @param contact
   *          the contact to remove
   * @return the contact removed or null
   */
  public Contact remove(UUID uniqueId) {
    return contacts.remove(uniqueId);
  }

  /**
   * Update a contact by replacing it with another. If no contact exists with
   * the same UUID, the new contact is inserted and null is returned.
   * 
   * @param contact
   *          the new contact
   * @return the contact replaced or null of no contact replaced.
   */
  public Contact update(Contact contact) {
    return add(contact);
  }

  /**
   * Find a contact by its unique ID. This is useful if client code links a
   * contact in this address book API with an external object. In that case, the
   * client can store the contact UUID and use this method to fetch the full
   * instance of the contact when needed.
   * 
   * @param targetUniqueId
   *          the unique ID for the desired contact
   * @return the contact or null if not found
   */
  public Contact find(UUID targetUniqueId) {
    return contacts.get(targetUniqueId);
  }

  /**
   * Returns a list of all of the contacts in alphabetical order.
   * 
   * @see ContactNameComparator
   * @return list of all contacts
   */
  public List<Contact> all() {
    ArrayList<Contact> foundContacts = new ArrayList<Contact>();

    for (Contact contact : contacts.values()) {
      foundContacts.add(contact);
    }

    Collections.sort(foundContacts, new ContactNameComparator());
    return foundContacts;
  }

  /**
   * Returns a list of contacts whose attributes match a query string. A null or
   * empty String will return all of the contacts. A contact is a match if every
   * space-separated term of the query string appears in at least one or more of
   * its attributes.
   * 
   * @param query
   *          the term use for comparison
   * @return list of contacts matching the query
   */
  public List<Contact> search(String query) {

    if (Util.isNullOrEmptyOrWhitespace(query)) {
      return all();
    }

    ArrayList<Contact> foundContacts = new ArrayList<Contact>();

    query = Util.replaceSpecialCharactersWithSpaces(query);

    for (Contact contact : contacts.values()) {
      boolean contactMatchesAllQueryTerms = true;

      for (String term : query.split(" ")) {
        if (!contact.attributesContainQueryTerm(term)) {
          contactMatchesAllQueryTerms = false;
          break;
        }
      }

      if (contactMatchesAllQueryTerms) {
        foundContacts.add(contact);
      }

    }

    Collections.sort(foundContacts, new ContactNameComparator());
    return foundContacts;
  }

  /**
   * Save the contents of this address book to a specified file in JSON format
   * with spacing and indentation. Null values are omitted. Serialization
   * handled by Jackson library https://github.com/FasterXML/jackson
   * 
   * @param targetFile
   *          the file to be used for storage.
   * @throws IOException
   *           if the file specified is inaccessible
   */
  public void writeTo(File targetFile) throws IOException {
    ObjectMapper mapper = new ObjectMapper();

    // allow private instance variables to be serialized
    mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

    // pretty print the JSON output
    mapper.enable(SerializationFeature.INDENT_OUTPUT);

    // suppress writing attributes whose value is null
    mapper.setSerializationInclusion(Include.NON_NULL);

    mapper.writeValue(targetFile, contacts);
  }

  /**
   * Replace the contents of this address book with data in a JSON file. Any
   * missing contact attributes are set to null. Deserialization handled by
   * Jackson library https://github.com/FasterXML/jackson
   * 
   * @param targetFile
   *          the file with address book data in JSON format.
   * @throws JsonMappingException
   *           if the JSON data contained in targetFile does not correctly map
   *           to the Java class
   * @throws JsonParseException
   *           if targetFile does not contain valid JSON
   * @throws IOException
   *           if the file is inaccessible
   */
  public void readFrom(File targetFile) throws JsonParseException,
      JsonMappingException, IOException {
    ObjectMapper mapper = new ObjectMapper();

    TypeReference<HashMap<UUID, Contact>> hashMapTypeReference =
        new TypeReference<HashMap<UUID, Contact>>() {
        };

    contacts = mapper.readValue(targetFile, hashMapTypeReference);
  }
}
