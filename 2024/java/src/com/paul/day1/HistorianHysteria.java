package com.paul.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistorianHysteria {
  
  public static void main(String[] args) {
    
    int sum = 0;
    
    List<Integer> leftList = new ArrayList<>();
    List<Integer> rightList = new ArrayList<>();
    
    //Test
    String filePath = "input/2024/day1/HistorianHysteriaTest.txt";
    
    //Real
    //String filePath = "input/2024/day1/HistorianHysteria.txt"; // Replace with your file path
    
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
    
    Collections.sort(leftList);
    Collections.sort(rightList);
    
    for (int i = 0; i < leftList.size(); i++) {
      //Math.abs(leftList.get(i) - rightList.get(i));
      sum += Math.abs(leftList.get(i) - rightList.get(i));
    }
    
    System.out.println("The sum is: " + sum);
    
  }
  
}






