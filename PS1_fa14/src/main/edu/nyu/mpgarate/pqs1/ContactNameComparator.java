package edu.nyu.mpgarate.pqs1;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 * Comparator for Contacts in alphabetical order according to the name
 * attribute.
 * 
 * @see Comparator
 * @author Michael Garate
 *
 */
class ContactNameComparator implements Comparator<Contact> {

  private static final Collator enUSCollator = Collator.getInstance(new Locale(
      "en", "US"));
  private static final int NAMES_MATCH = 0;
  private static final int C1_IS_EARLIER = -1;
  private static final int C1_IS_LATER = 1;

  /**
   * Returns an alphanumeric comparison between this contact and another
   * according to the name field.
   * 
   * @param c1
   *          the first contact to compare
   * @param c2
   *          the second contact to compare
   * @return -1 if the first contact comes alphabetically earlier, 1 if the
   *         second comes alphabetically earlier, and 0 if the names match
   *         according to String equals().
   */
  @Override
  public int compare(Contact c1, Contact c2) {

    if (null == c1 || null == c2) {
      throw new IllegalArgumentException("Must compare two contacts");
    }

    String name1 = c1.getName();
    String name2 = c2.getName();

    if (name1 == null && name2 == null) {
      return NAMES_MATCH;
    }

    if (null == name1) {
      return C1_IS_EARLIER;
    }

    if (null == name2) {
      return C1_IS_LATER;
    }

    if (name1.equals(name2)) {
      return NAMES_MATCH;
    }

    return enUSCollator.compare(name1, name2);
  }
}
