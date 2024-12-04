package com.paul.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MullItOver {

  public static void main(String[] args) {
    
    //Test
    //String filePath = "input/2024/day3/MullItOverTest.txt";
    
    //Real
    String filePath = "input/2024/day3/MullItOver.txt";
    
    int sum = 0;
    
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        Matcher matcher = pattern.matcher(line);
        
        while (matcher.find()) {
          sum += getMulValue(matcher.group());
        }
        
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    System.out.println(sum);
    
  }
  
  public static Integer getMulValue(String input) {
    String[] parts = input.replaceAll("mul\\(","")
      .replaceAll("\\)","")
      .split(",");
    
    return Integer.parseInt(parts[0]) * Integer.parseInt(parts[1]);
    
  }
}
