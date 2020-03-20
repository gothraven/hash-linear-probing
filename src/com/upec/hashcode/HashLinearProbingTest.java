package com.upec.hashcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.IconUIResource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName ("Class: HashLinearProbing (capacity)")
class HashLinearProbingTest {

  @Test
  @DisplayName ("Function: Put (key, value)")
  void put() {
    testPutWithOneValue();
    testPutWithOverlappingKeys();
    testPutWithOverTheEdgeOverlappingKeys();
    testPutWithFullCapacity();
  }

  private void testPutWithOneValue() {
    HashLinearProbing hlp = new HashLinearProbing(10);
    hlp.put("A", "Something");
    assertEquals("A", hlp.getKeys()[5]);
    assertEquals("Something", hlp.getValues()[5]);
    assertEquals(1, hlp.getSize());
  }

  private void testPutWithOverlappingKeys() {
    HashLinearProbing hlp = new HashLinearProbing(10);
    hlp.put("AB", "first");
    hlp.put("BA", "second");
    assertEquals("AB", hlp.getKeys()[1]);
    assertEquals("first", hlp.getValues()[1]);
    assertEquals("BA", hlp.getKeys()[2]);
    assertEquals("second", hlp.getValues()[2]);
    assertEquals(2, hlp.getSize());
  }

  private void testPutWithOverTheEdgeOverlappingKeys() {
    HashLinearProbing hlp = new HashLinearProbing(10);
    hlp.put("ZO", "first");
    hlp.put("OZ", "second");
    assertEquals("ZO", hlp.getKeys()[9]);
    assertEquals("first", hlp.getValues()[9]);
    assertEquals("OZ", hlp.getKeys()[0]);
    assertEquals("second", hlp.getValues()[0]);
    assertEquals(2, hlp.getSize());
  }

  private void testPutWithFullCapacity() {
    HashLinearProbing hlp = new HashLinearProbing(10);
    String[] keys = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
    for (int i = 0; i < keys.length; i++) {
      hlp.put(keys[i], "Value " + i);
    }
    assertEquals(10, hlp.getSize());
  }

  @Test
  @DisplayName ("Function: Get (key)")
  void get() {
    testGetWithOneValue();
    testGetWithOverlappingKeys();
    testGetWithOverTheEdgeOverlappingKeys();
    testGetWithFullCapacity();
  }

  private void testGetWithOneValue() {
    HashLinearProbing hlp = new HashLinearProbing(10);
    hlp.put("A", "Something");
    assertEquals("Something", hlp.get("A"));
  }

  private void testGetWithOverlappingKeys() {
    HashLinearProbing hlp = new HashLinearProbing(10);
    hlp.put("AB", "first");
    hlp.put("BA", "second");
    assertEquals("first", hlp.get("AB"));
    assertEquals("second", hlp.get("BA"));
  }

  private void testGetWithOverTheEdgeOverlappingKeys() {
    HashLinearProbing hlp = new HashLinearProbing(10);
    hlp.put("ZO", "first");
    hlp.put("OZ", "second");
    assertEquals("first", hlp.get("ZO"));
    assertEquals("second", hlp.get("OZ"));
  }

  private void testGetWithFullCapacity() {
    HashLinearProbing hlp = new HashLinearProbing(10);
    String[] keys = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
    for (int i = 0; i < keys.length; i++) {
      hlp.put(keys[i], "Value " + i);
    }
    for (int i = 0; i < keys.length; i++) {
      if (keys[i].equals("K")) assertNull(hlp.get(keys[i]));
      else assertEquals("Value " + i, hlp.get(keys[i]));
    }
  }

  @Test
  @DisplayName ("Function: Remove (key)")
  void remove() {
    testRemoveWithOneValue();
    testRemoveWithOverlappingKeys();
    testRemoveWithOverTheEdgeOverlappingKeys();
    testRemoveWithFullCapacity();
  }

  private void testRemoveWithOneValue() {
    HashLinearProbing hlp = new HashLinearProbing(10);
    hlp.put("A", "Something");
    hlp.remove("A");
    assertNull(hlp.get("A"));
  }

  private void testRemoveWithOverlappingKeys() {
    HashLinearProbing hlp = new HashLinearProbing(10);
    hlp.put("AB", "first");
    hlp.put("BA", "second");
    hlp.remove("BA");
    assertEquals("first", hlp.get("AB"));
    assertNull(hlp.get("BA"));
  }

  private void testRemoveWithOverTheEdgeOverlappingKeys() {
    HashLinearProbing hlp = new HashLinearProbing(10);
    hlp.put("ZO", "first");
    hlp.put("OZ", "second");
    hlp.remove("OZ");
    assertEquals("first", hlp.get("ZO"));
    assertNull(hlp.get("OZ"));
  }

  private void testRemoveWithFullCapacity() {
    HashLinearProbing hlp = new HashLinearProbing(10);
    String[] keys = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
    for (int i = 0; i < keys.length; i++) {
      hlp.put(keys[i], "Value " + i);
    }
    hlp.remove("A");
    hlp.remove("F");
    assertNull(hlp.get("A"));
    assertNull(hlp.get("F"));
  }
}