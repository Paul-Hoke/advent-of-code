package com.paul.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.paul.day1.Elf;

public class RockPaperScissors {
  
  public static void main(String[] args) {
    
    //Test
    //String filePath = "input/2022/day2/RockPaperScissorsTest.txt";
    
    //Real
    String filePath = "input/2022/day2/RockPaperScissors.txt";
    
    List<Round> rounds = new ArrayList<>();
    int totalPoints = 0;
    
    try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] roundParts = line.split(" ");
        rounds.add(new Round(roundParts[0], roundParts[1]));
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
    
    for(Round round : rounds) {
      totalPoints += round.pointsWon;
    }
    
    System.out.println("Total points: " + totalPoints);
    
  }
  
  static class Round {
    
    int pointsWon;
    
    public Round(String theirs, String yours) {
      this.pointsWon = getBasePoints(yours);
      
      String theirPlay = transformInput(theirs);
      
      //Draw
      if(theirPlay.equals(yours)) {
        this.pointsWon += 3;
      }
      
      //Games where I win
      //Rock beats Scissors
      if((yours.equals("X") && theirPlay.equals("Z"))
        || (yours.equals("Y") && theirPlay.equals("X"))
        || (yours.equals("Z") && theirPlay.equals("Y"))) {
        this.pointsWon += 6;
      }
    }
    
    private int getBasePoints(String play) {
      //X for Rock, Y for Paper, and Z for Scissors
      //1 for Rock, 2 for Paper, and 3 for Scissors
      if(play.equals("X")) return 1;
      if(play.equals("Y")) return 2;
      if(play.equals("Z")) return 3;
      return 0;
    }
    
    private String transformInput(String input) {
      //A for Rock, B for Paper, and C for Scissors
      if(input.equals("A")) return "X";
      if(input.equals("B")) return "Y";
      if(input.equals("C")) return "Z";
      return "";
    }
  }
}
