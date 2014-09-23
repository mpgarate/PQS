package edu.nyu.mpgarate.pqs1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.nyu.mpgarate.pqs1.Util;

public class UtilTest {

  @Test
  public void emptyStringIsEmpty() {
    String str = "";
    assertEquals(true, Util.isNullOrEmptyOrWhitespace(str));
  }

  @Test
  public void spacesStringIsWhitespace() {
    String str = "       ";
    assertEquals(true, Util.isNullOrEmptyOrWhitespace(str));
  }

  @Test
  public void tabbedStringIsWhitespace() {
    String str = "\t\t\t";
    assertEquals(true, Util.isNullOrEmptyOrWhitespace(str));
  }

  @Test
  public void newlineStringIsWhitespace() {
    String str = "\n\n\n";
    assertEquals(true, Util.isNullOrEmptyOrWhitespace(str));
  }

  @Test
  public void specialCharactersAreReplaced() {
    String str = "!@#$%^&*()_+{}|:<>?~`-=\\][';/.,']";

    String result = Util.replaceSpecialCharactersWithSpaces(str);

    assertEquals(" ", result);
  }
}
