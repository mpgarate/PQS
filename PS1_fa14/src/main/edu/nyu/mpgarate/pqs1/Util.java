package edu.nyu.mpgarate.pqs1;

/**
 * Static utility functions with logic not tied to a particular class. These are
 * intended for internal reuse and code readability.
 * 
 * @author Michael Garate
 *
 */
class Util {
  /**
   * Determine if a string is null, is empty, or consists of only whitespace.
   * 
   * @param str
   *          the string to be checked
   * @return true if the string is null, is empty, or consists of only
   *         whitespace. False otherwise.
   */
  static boolean isNullOrEmptyOrWhitespace(String str) {
    return null == str || 0 == str.trim().length();
  }

  /**
   * Replace any non-alphanumeric characters in a string with spaces. Used
   * http://www.regexr.com/ to test regex cases as I wrote it. Adjacent spaces
   * are replaced such that only 1 space will separate terms.
   * 
   * @param str
   *          the source string
   * @return the target string with a single space separating any alphanumeric
   *         characters.
   */
  static String replaceSpecialCharactersWithSpaces(String str) {
    str = str.replaceAll("[^\\dA-Za-z]", " ");

    while (str.indexOf("  ") > -1) {
      str = str.replace("  ", " ");
    }

    return str;
  }
}
