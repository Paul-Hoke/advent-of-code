package com.paul.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CeresSearch {
  
  public static void main(String[] args) {
    
    //Test
    //String filePath = "input/2024/day4/CeresSearchTest1.txt";
    
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
        if (grid.get(i).get(j).equals("X")) {
          if (checkRight(i, j, grid)) count++;
          if (checkLeft(i, j, grid)) count++;
          if(checkUp(i, j, grid)) count++;
          if(checkDown(i, j, grid)) count++;
          if(checkDownRight(i, j, grid)) count++;
          if(checkUpRight(i, j, grid)) count++;
          if(checkDownLeft(i, j, grid)) count++;
          if(checkUpLeft(i, j, grid)) count++;
        }
      }
    }
    
    System.out.println("The count is: " + count);
    
  }
  
  public static boolean checkRight(int x, int y, List<List<String>> grid) {
    try {
      if(grid.get(x).get(y+1).equals("M")
        && grid.get(x).get(y+2).equals("A")
        && grid.get(x).get(y+3).equals("S")) {
        return true;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      //Do nothing
    } catch (IndexOutOfBoundsException e){
      //Do nothing
    }
    return false;
  }
  
  public static boolean checkLeft(int x, int y, List<List<String>> grid) {
    try {
      if(grid.get(x).get(y-1).equals("M")
        && grid.get(x).get(y-2).equals("A")
        && grid.get(x).get(y-3).equals("S")) {
        return true;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      //Do nothing
    } catch (IndexOutOfBoundsException e){
      //Do nothing
    }
    
    return false;
  }
  
  public static boolean checkUp(int x, int y, List<List<String>> grid) {
    try {
      if(grid.get(x-1).get(y).equals("M")
        && grid.get(x-2).get(y).equals("A")
        && grid.get(x-3).get(y).equals("S")) {
        return true;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      //Do nothing
    } catch (IndexOutOfBoundsException e){
      //Do nothing
    }
    
    return false;
  }
  
  public static boolean checkDown(int x, int y, List<List<String>> grid) {
    try {
      if(grid.get(x+1).get(y).equals("M")
        && grid.get(x+2).get(y).equals("A")
        && grid.get(x+3).get(y).equals("S")) {
        return true;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      //Do nothing
    } catch (IndexOutOfBoundsException e){
      //Do nothing
    }
    
    return false;
  }
  
  public static boolean checkDownRight(int x, int y, List<List<String>> grid) {
    try {
      if(grid.get(x+1).get(y+1).equals("M")
        && grid.get(x+2).get(y+2).equals("A")
        && grid.get(x+3).get(y+3).equals("S")) {
        return true;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      //Do nothing
    } catch (IndexOutOfBoundsException e){
      //Do nothing
    }
    
    return false;
  }
  
  public static boolean checkUpRight(int x, int y, List<List<String>> grid) {
    try {
      if(grid.get(x-1).get(y+1).equals("M")
        && grid.get(x-2).get(y+2).equals("A")
        && grid.get(x-3).get(y+3).equals("S")) {
        return true;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      //Do nothing
    } catch (IndexOutOfBoundsException e){
      //Do nothing
    }
    
    return false;
  }
  
  public static boolean checkDownLeft(int x, int y, List<List<String>> grid) {
    try {
      if(grid.get(x+1).get(y-1).equals("M")
        && grid.get(x+2).get(y-2).equals("A")
        && grid.get(x+3).get(y-3).equals("S")) {
        return true;
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      //Do nothing
    } catch (IndexOutOfBoundsException e){
      //Do nothing
    }
    
    return false;
  }
  
  public static boolean checkUpLeft(int x, int y, List<List<String>> grid) {
    try {
      if(grid.get(x-1).get(y-1).equals("M")
        && grid.get(x-2).get(y-2).equals("A")
        && grid.get(x-3).get(y-3).equals("S")) {
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
