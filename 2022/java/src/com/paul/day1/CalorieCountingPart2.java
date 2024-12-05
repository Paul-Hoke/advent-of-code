package com.paul.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalorieCountingPart2 {
  
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
    
    elfList.sort(elfComparator);
    
    System.out.println("The Top 3 calories is : "
    + (elfList.get(elfList.size() - 1).totalCalories
      + elfList.get(elfList.size() - 2).totalCalories
      + elfList.get(elfList.size() - 3).totalCalories));
  }
  
}
