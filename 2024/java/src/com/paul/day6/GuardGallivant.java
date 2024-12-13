package com.paul.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.EqualsAndHashCode;

public class GuardGallivant {
  
  public static void main(String[] args) throws InterruptedException {
    
    //Test
    //String filePath = "input/2024/day6/GuardGallivantTest.txt";
    
    //Test
    String destFilePath = "input/2024/day6/results.txt";
    
    //Real
    String filePath = "input/2024/day6/GuardGallivant.txt";
    
    List<List<String>> grid = new ArrayList<List<String>>();
    int yCount = 0;
    Guard guard = new Guard();
    
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        List<String> row = Arrays.asList(line.split(""));
        grid.add(row);
        for(int i = 0; i < row.size(); i++) {
          if(row.get(i).equals("^")) {
            guard.yPos = yCount;
            guard.xPos = i;
            //guard.uniquePositions.add(String.valueOf(i) + String.valueOf(yCount));
            guard.visitedNodes.add(new Node(i, yCount));
          }
        }
        yCount++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    guard.grid = grid;
    
    System.out.println("The guard is at X: " + guard.xPos + " Y: " + guard.yPos + " and is facing: " + guard.direction);
    
    while(true) {
      //Thread.sleep(50);
      try {
        try (FileWriter writer = new FileWriter(destFilePath)) {
          for(List<String> row : guard.grid) {
            writer.write(String.join("", row) + "\n");
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
        guard.move();
      } catch (RuntimeException e) {
        System.out.println("The guard has left the grid");
        System.out.println("Last known position - X: " + guard.xPos + " Y: " + guard.yPos);
        break;
      }
    }
    
    System.out.println("Distinct positions visited is: " + guard.visitedNodes.size());
  }
}

class Guard {
  int xPos = 0;
  int yPos = 0;
  Direction direction = Direction.UP;
  List<List<String>> grid;
  Set<Node> visitedNodes = new HashSet<>();
  
  void move() {
    if(peek().equals("#")) {
      this.direction = getNewDirection();
      if(peek().equals("#")) {
        this.direction = getNewDirection();
        if(peek().equals("#")) {
          this.direction = getNewDirection();
        }
      }
    }
    
    switch(this.direction) {
      case UP -> this.yPos -= 1;
      case RIGHT -> this.xPos += 1;
      case DOWN -> this.yPos += 1;
      case LEFT -> this.xPos -= 1;
    }
    
    this.visitedNodes.add(new Node(this.xPos, this.yPos));
    this.grid.get(this.yPos).set(this.xPos, "^");
    
  }
  
  String peek() {
    return switch (this.direction) {
      case UP -> grid.get(yPos - 1).get(xPos);
      case RIGHT -> grid.get(yPos).get(xPos + 1);
      case DOWN -> grid.get(yPos + 1).get(xPos);
      case LEFT -> grid.get(yPos).get(xPos - 1);
    };
  }
  
  Direction getNewDirection() {
    return switch (this.direction) {
      case UP -> Direction.RIGHT;
      case RIGHT -> Direction.DOWN;
      case DOWN -> Direction.LEFT;
      case LEFT -> Direction.UP;
    };
  }
}

enum Direction {
  UP, DOWN, LEFT, RIGHT;
}

@EqualsAndHashCode
class Node {
  int x,y;
  
  Node(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
