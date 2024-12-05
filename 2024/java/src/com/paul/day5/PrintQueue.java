package com.paul.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintQueue {
  
  public static void main(String[] args) {
    
    //Test
    //String filePath = "input/2024/day5/PrintQueueTest.txt";
    
    //Real
    String filePath = "input/2024/day5/PrintQueue.txt";
    
    int count = 0;
    int count2 = 0;
    Map<Integer, Rules> ruleMap = new HashMap<>();
    List<List<Integer>> pageLineList = new ArrayList<>();
    List<List<Integer>> validPageLineList = new ArrayList<>();
    List<List<Integer>> invalidPageLineList = new ArrayList<>();
    List<List<Integer>> allRules = new ArrayList<>();
    
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        if(line.contains("|")) {
          String[] ruleParts = line.split("\\|");
          Integer left = Integer.parseInt(ruleParts[0]);
          Integer right = Integer.parseInt(ruleParts[1]);
          
          List<Integer> ruleList = new ArrayList<>();
          ruleList.add(left);
          ruleList.add(right);
          
          allRules.add(ruleList);
          
          if(ruleMap.containsKey(left)) {
            ruleMap.get(left).afterList.add(right);
          } else {
            Rules newRules = new Rules();
            newRules.afterList.add(right);
            ruleMap.put(left, newRules);
          }
          
          if(ruleMap.containsKey(right)) {
            ruleMap.get(right).beforeList.add(left);
          } else {
            Rules newRules = new Rules();
            newRules.beforeList.add(left);
            ruleMap.put(right, newRules);
          }
        } else if(line.contains(",")) {
          List<Integer> pageLine = new ArrayList<>();
          String[] lineParts = line.split(",");
          for(String page : lineParts) {
            pageLine.add(Integer.parseInt(page));
          }
          pageLineList.add(pageLine);
        }
      }
      
      for(List<Integer> pageLine : pageLineList) {
        boolean isValidPageLine = true;
        for(int i = 0; i < pageLine.size(); i++) {
          System.out.println("The pages for this line are: " + pageLine);
          System.out.println("The current page is " + pageLine.get(i));
          System.out.println("These must not be before:" + ruleMap.get(pageLine.get(i)).afterList);
          System.out.println("These must not be after:" + ruleMap.get(pageLine.get(i)).beforeList);
          System.out.println();
          
          List<Integer> beforeList = pageLine.subList(0, i);
          for(int page : beforeList) {
            if(ruleMap.get(pageLine.get(i)).afterList.contains(page)) {
              isValidPageLine = false;
              break;
            }
          }
          
          List<Integer> afterList = pageLine.subList(i, pageLine.size());
          for(int page : afterList) {
            if(ruleMap.get(pageLine.get(i)).beforeList.contains(page)) {
              isValidPageLine = false;
              break;
            }
          }
        }
        if(isValidPageLine) {
          validPageLineList.add(pageLine);
        } else {
          invalidPageLineList.add(pageLine);
        }
      }
      
      for(List<Integer> pageLine : validPageLineList) {
        count += pageLine.get((pageLine.size() - 1)/2);
      }
      
      PageRuleComparator pageRuleComparator = new PageRuleComparator(allRules);
      for(List<Integer> pageLine : invalidPageLineList) {
        pageLine.sort(pageRuleComparator);
      }
      
      for(List<Integer> pageLine : invalidPageLineList) {
        count2 += pageLine.get((pageLine.size() - 1)/2);
      }
      
      //Part1 answer
      System.out.println("The sum of the middle numbers of the valid updates is: " + count);
      
      //Part2 answer
      System.out.println("The sum of the middle numbers of the valid updates is: " + count2);
      
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
}

class Rules {
  
  List<Integer> beforeList = new ArrayList<>();
  List<Integer> afterList = new ArrayList<>();
  
}

class PageRuleComparator implements Comparator<Integer> {
  
  List<List<Integer>> allRules;
  
  public PageRuleComparator(List<List<Integer>> allRules) {
    this.allRules = allRules;
  }
  
  public int compare(Integer left, Integer right) {
    for(List<Integer> rule : allRules) {
      if(rule.contains(left) && rule.contains(right)) {
        int leftIndex = 0;
        int rightIndex = 0;
        
        for(int i = 0; i < rule.size(); i++) {
          if(rule.get(i) == left) {
            leftIndex = i;
          }
        }
        
        for(int i = 0; i < rule.size(); i++) {
          if(rule.get(i) == right) {
            rightIndex = i;
          }
        }
        if(leftIndex < rightIndex) {
          return -1;
        } else {
          return 1;
        }
      }
    }
    return 0;
  }
  
}
