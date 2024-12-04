package com.paul.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistorianHysteriaPart2 {
  
  public static void main(String[] args) {
    
    int sum = 0;
    
    List<Integer> leftList = new ArrayList<>();
    List<Integer> rightList = new ArrayList<>();
    List<KeyCount> keyCountList = new ArrayList<>();
    
    String filePath = "input/2024/HistorianHysteria.txt"; // Replace with your file path
    
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split("   ");
        leftList.add(Integer.parseInt(parts[0]));
        rightList.add(Integer.parseInt(parts[1]));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    for(Integer value : leftList) {
      keyCountList.add(new KeyCount(value, 0));
    }
    
    for(Integer value : rightList) {
      for(KeyCount keyCount : keyCountList) {
        if (value.equals(keyCount.key)) {
          keyCount.count++;
        }
      }
    }
    
    for(KeyCount keyCount : keyCountList) {
      sum += keyCount.count * keyCount.key;
    }
    
    System.out.println("The sum is: " + sum);
    
  }
  
}
