package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    boolean check = false;
    while (!check) {
      System.out.print("Septi input : ");
      String s2 = br.readLine();
      try {
        int i2 = Integer.parseInt(s2);
        System.out.println("Septi output : " + i2);
        check = true;
          System.out.println("");
          System.out.println("Thank You :)");
      } catch (Exception e) {
          System.out.println("please input correct number!");
          System.out.println("");
      }
    }
  }
}
