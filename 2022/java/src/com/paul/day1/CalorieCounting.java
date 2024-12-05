package com.paul.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalorieCounting {
  
  public static void main(String[] args) {
    
    //Test
    //String filePath = "input/2022/day1/CalorieCountingTest.txt";
    
    //Real
    String filePath = "input/2022/day1/CalorieCounting.txt";
    
    List<Elf> elfList = new ArrayList<>();
    
    try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      Elf elf = new Elf();
      while ((line = br.readLine()) != null) {
        if(!line.equals("")) {
          elf.totalCalories += Integer.parseInt(line);
        } else {
          elfList.add(elf);
          elf = new Elf();
        }
      }
      //Need to make sure to capture the last elf!
      elfList.add(elf);
    } catch(IOException e) {
      e.printStackTrace();
    }
    
    ElfComparator elfComparator = new ElfComparator();
    
    System.out.println("Most calories carried by a single elf is: " + elfList.stream().max(elfComparator).get());
  }
}
