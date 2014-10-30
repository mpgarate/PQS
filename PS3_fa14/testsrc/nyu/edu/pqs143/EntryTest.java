package nyu.edu.pqs143;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EntryTest {

  @Test
  public void testEquals_sameObjectReference() {
    Entry entry = new Entry.Builder("John").build();

    assertTrue(entry.equals(entry));
  }

  @Test
  public void testEquals_notEqualNull() {
    Entry entry = new Entry.Builder("John").build();
    assertFalse(entry.equals(null));
  }

  /**
   * Test fails because the equals method not implemented. While the behavior in
   * this test might be intended by the code as written, the assignment
   * instructions call for an implementation of equals().
   */

  @Test
  public void _failingTestEquals_simple() {
    Entry entry1 = new Entry.Builder("John").build();
    Entry entry2 = new Entry.Builder("John").build();

    assertTrue(entry1.equals(entry2));
    assertTrue(entry2.equals(entry1));
  }

}
