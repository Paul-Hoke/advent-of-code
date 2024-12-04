package com.paul.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RedNosedReports {
  
  public static void main(String[] args) {
    
    //Test
    //String filePath = "input/2024/day2/RedNosedReportsTest.txt";
    
    //Real
    String filePath = "input/2024/day2/RedNosedReports.txt";
    
    int count = 0;
    
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        List<String> lineList = new ArrayList<>(Arrays.asList(line.split(" ")));
        
        if((isAllAscending(lineList) || isAllDescending(lineList)) && isAbsWithinBounds(lineList)) {
          count++;
        }
        
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    System.out.println("count is: " + count);
  
  }
  
  public static boolean isAllAscending(List<String> list) {
    for (int i = 0; i < list.size() - 1; i++) {
      if(Integer.parseInt(list.get(i)) - Integer.parseInt(list.get(i + 1)) >= 0) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean isAllDescending(List<String> list) {
    for (int i = 0; i < list.size() - 1; i++) {
      if(Integer.parseInt(list.get(i)) - Integer.parseInt(list.get(i + 1)) <= 0) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean isAbsWithinBounds(List<String> list) {
    for (int i = 0; i < list.size() - 1; i++) {
      if(Math.abs(Integer.parseInt(list.get(i)) - Integer.parseInt(list.get(i + 1))) > 3) {
        return false;
      }
    }
    return true;
  }
}
