package com.paul.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GiftShop {
  public static void main(String[] args) {
    String filePath = "input/2025/day2/GiftShopTest.txt";
    //String filePath = "input/2025/day2/GiftShop.txt";
    long total = 0L;
    
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        
        String[] lineParts = line.split(",");
        
        for(String range : lineParts) {
          
          String[] rangeParts = range.split("-");
          long rangeStart = Long.parseLong(rangeParts[0]);
          long rangeEnd = Long.parseLong(rangeParts[1]);
          
          for(long i = rangeStart; i <= rangeEnd; i++) {
            if (Long.toString(i).length() == 1 || Long.toString(i).length() % 2 != 0) continue;
            
            String currentNumber = Long.toString(i);
            String firstHalf = currentNumber.substring(0, currentNumber.length() / 2);
            String secondHalf = currentNumber.substring(currentNumber.length() / 2);
            
            if (firstHalf.equals(secondHalf)) {
              // This is string concatenation not addition!
              total += Long.parseLong(firstHalf + secondHalf);
            }
          }
          
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    System.out.println("Answer: " + total);
  }
}
