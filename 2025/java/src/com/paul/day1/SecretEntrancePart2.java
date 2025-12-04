package com.paul.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SecretEntrancePart2 {
  public static void main(String[] args) {
    String filePath = "input/2025/day1/SecretEntranceTest.txt";
    //String filePath = "input/2025/day1/SecretEntrance.txt";
    int dialPosition = 50;
    int timesHitZero = 0;
    
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) continue;
        
        char direction = line.charAt(0);
        int distance = Integer.parseInt(line.substring(1));
        
        if (direction == 'L') {
          for(int i = distance; i > 0; i--) {
            dialPosition = (dialPosition == 0) ? 99 : dialPosition - 1 ;
            
            if (dialPosition == 0) {
              timesHitZero++;
            }
          }
          
        } else {
          for(int i = 0; i < distance; i++) {
            dialPosition = (dialPosition == 99) ? 0 : dialPosition + 1 ;
            
            if (dialPosition == 0) {
              timesHitZero++;
            }
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    System.out.println("Password: " + timesHitZero);
  }
}

