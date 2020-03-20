package com.upec.hashcode;

public class Main {

  public static void main(String[] args) {
    HashLinearProbing hlp = new HashLinearProbing(10);
    hlp.put("A", "first");
    System.out.println(hlp);
    hlp.get("A");
    System.out.println(hlp);
    hlp.remove("A");
    System.out.println(hlp);
  }
}
