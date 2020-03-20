package com.upec.hashcode;

import java.util.Arrays;

public class HashLinearProbing {
  private int M;
  private String[] keys;
  private String[] values;
  private int size;

  public HashLinearProbing(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity can not be less than or equal to 0");
    }
    this.M = capacity;
    this.size = 0;
    this.keys = new String[this.M];
    this.values = new String[this.M];
  }

  private int hash(String key) {
    int hash = 0;
    for (int i = 0; i < key.length(); i++) {
      hash += key.charAt(i);
    }
    return hash % this.M;
  }

  public void put(String key, String value) {
    if (size == this.M) return;
    int position = this.hash(key);

    if (this.keys[position] != null) {
      do {
        position = (++ position) % this.M;
      } while (this.keys[position] != null);
    }
    this.keys[position] = key;
    this.values[position] = value;
    this.size++;
  }

  public String get(String key) {
    int position = this.hash(key);

    if (this.keys[position] != null) {
      if (this.keys[position].equals(key)) {
        return this.values[position];
      } else {
        int initialIndex = position;
        do {
          position = (++ position) % this.M;
          if (position == initialIndex)
            return null;
        } while (this.keys[position] == null || ! this.keys[position].equals(key));
        if (this.keys[position].equals(key)) {
          return this.values[position];
        }
      }
    }
    return null;
  }

  public void remove(String key) {
    if (this.size == 0) return;
    int position = this.hash(key);

    if (this.keys[position] != null) {
      if (this.keys[position].equals(key)) {
        this.keys[position] = null;
      } else {
        do {
          position = (++ position) % this.M;
        } while (this.keys[position] == null || ! this.keys[position].equals(key));
        if (this.keys[position].equals(key)) {
          this.keys[position] = null;
        }
      }
      String tmpKey;
      String tmpValue;
      while (this.keys[position] != null) {
        tmpKey = this.keys[position];
        tmpValue = this.values[position];
        keys[position] = null;
        values[position] = null;
        this.put(tmpKey, tmpValue);
        position = (position + 1) % this.M;
      }
      this.size--;
    }
  }

  public String[] getKeys() {
    return keys;
  }

  public String[] getValues() {
    return values;
  }

  public int getSize() {
    return size;
  }

  @Override
  public String toString() {
    return "HashLinearProbing{" +
        "M=" + M + "\n" +
        ", keys=" + Arrays.toString(keys) + "\n" +
        ", values=" + Arrays.toString(values) + "\n" +
        '}';
  }
}
