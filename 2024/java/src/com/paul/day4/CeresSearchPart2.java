package com.paul.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CeresSearchPart2 {
  
  public static void main(String[] args) {
    
    //Test
    //String filePath = "input/2024/day4/CeresSearchPart2Test1.txt";
    
    //Real
    String filePath = "input/2024/day4/CeresSearch.txt";
    
    int count = 0;
    List<List<String>> grid = new ArrayList<List<String>>();
    
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        List<String> row = Arrays.asList(line.split(""));
        grid.add(row);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    for(int i = 0; i < grid.size(); i++) {
      for(int j = 0; j < grid.get(i).size(); j++) {
        
        //Start our search
        if (grid.get(i).get(j).equals("A")) {
          if(checkForCase1(i,j,grid)) count++;
          if(checkForCase2(i,j,grid)) count++;
          if(checkForCase3(i,j,grid)) count++;
          if(checkForCase4(i,j,grid)) count++;
        }
      }
    }
    
    System.out.println("The count is: " + count);
    
  }
  
  public static boolean checkForCase1(int x, int y, List<List<String>> grid) {
    
    /*
    M.S
    .A.
    M.S
     */
    
    try {
      if(grid.get(x-1).get(y+1).equals("S")
        && grid.get(x+1).get(y+1).equals("S")
        && grid.get(x-1).get(y-1).equals("M")
        && grid.get(x+1).get(y-1).equals("M")) {
        return true;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      //Do nothing
    } catch (IndexOutOfBoundsException e){
      //Do nothing
    }
    
    return false;
  }
  
  public static boolean checkForCase2(int x, int y, List<List<String>> grid) {
    
    /*
    S.M
    .A.
    S.M
     */
    
    try {
      if(grid.get(x-1).get(y+1).equals("M")
        && grid.get(x+1).get(y+1).equals("M")
        && grid.get(x-1).get(y-1).equals("S")
        && grid.get(x+1).get(y-1).equals("S")) {
        return true;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      //Do nothing
    } catch (IndexOutOfBoundsException e){
      //Do nothing
    }
    
    return false;
  }
  
  public static boolean checkForCase3(int x, int y, List<List<String>> grid) {
    
    /*
    S.S
    .A.
    M.M
     */
    
    try {
      if(grid.get(x-1).get(y+1).equals("S")
        && grid.get(x+1).get(y+1).equals("M")
        && grid.get(x-1).get(y-1).equals("S")
        && grid.get(x+1).get(y-1).equals("M")) {
        return true;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      //Do nothing
    } catch (IndexOutOfBoundsException e){
      //Do nothing
    }
    
    return false;
  }
  
  public static boolean checkForCase4(int x, int y, List<List<String>> grid) {
    
    /*
    M.M
    .A.
    S.S
     */
    
    try {
      if(grid.get(x-1).get(y+1).equals("M")
        && grid.get(x+1).get(y+1).equals("S")
        && grid.get(x-1).get(y-1).equals("M")
        && grid.get(x+1).get(y-1).equals("S")) {
        return true;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      //Do nothing
    } catch (IndexOutOfBoundsException e){
      //Do nothing
    }
    
    return false;
  }
  
}
