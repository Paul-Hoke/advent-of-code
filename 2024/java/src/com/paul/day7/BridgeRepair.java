package com.paul.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BridgeRepair {
  
  public static void main(String[] args) {
    
    //Test
    String filePath = "input/2024/day7/BridgeRepairTest.txt";
    
    //Real
    //String filePath = "input/2024/day7/BridgeRepair.txt";
    
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] lineParts = line.split(": ");
        int calibrationGoal = Integer.parseInt(lineParts[0]);
        List<Integer> calibrations = new ArrayList<>(Arrays.asList(lineParts[1].split(" ")))
          .stream()
          .map(Integer::parseInt)
          .toList();
        
        CalibrationNode root = new CalibrationNode(null, calibrations.getFirst());
        
        //Start our tree
        for(Integer calibration : calibrations) {
          CalibrationNode current = root;
          if(current.left == null || current.right == null) {
          
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("done");
  }
}

class CalibrationNode {
  CalibrationNode parent, left, right;
  int total;
  
  public CalibrationNode(CalibrationNode parent, int total) {
    this.parent = parent;
    this.total = total;
  }
}