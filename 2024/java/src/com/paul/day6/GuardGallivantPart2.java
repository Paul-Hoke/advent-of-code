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
import org.apache.commons.lang3.SerializationUtils;

public class GuardGallivantPart2 {
  
  public static void main(String[] args) throws InterruptedException {
    
    //Test
    String filePath = "input/2024/day6/GuardGallivantTest.txt";
    
    //Real
    //String filePath = "input/2024/day6/GuardGallivant.txt";
    
    List<List<String>> grid = getNewGrid(filePath);
    int startXPos = 0;
    int startYPos = 0;
    
    for(int i = 0; i < grid.size(); i++) {
      for(int j = 0; j < grid.get(i).size(); j++) {
        if(grid.get(i).get(j).equals("^")) {
          startXPos = j;
          startYPos = i;
        }
      }
    }
    
    for(int i = 0; i < grid.size(); i++) {
      for(int j = 0; j < grid.get(i).size(); j++) {
        
        List<List<String>> newGrid = getNewGrid(filePath);
        GuardPart2 guard = new GuardPart2(startXPos, startYPos, newGrid, i, j);
        
        while(true) {
          //Thread.sleep(50);
          try {
            guard.move();
          } catch (RuntimeException e) {
            System.out.println("The guard has left the grid");
            System.out.println("Last known position - X: " + guard.xPos + " Y: " + guard.yPos);
            break;
          } catch (LoopException e) {
            System.out.println(e.getMessage());
            break;
          }
        }
      }
    }
    
    System.out.println("done");
  }
  
  static List<List<String>> getNewGrid(String filePath) {
    List<List<String>> grid = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        List<String> row = Arrays.asList(line.split(""));
        grid.add(row);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return grid;
  }
  
}

class GuardPart2 {
  int xPos = 0;
  int yPos = 0;
  Direction direction = Direction.UP;
  List<List<String>> grid;
  Set<NodePart2> visitedNodes = new HashSet<>();
  
  GuardPart2(int startXPos, int startYPos, List<List<String>> grid, int obstacleXPos, int obstacleYPos) {
    this.xPos = startXPos;
    this.yPos = startYPos;
    this.grid = grid;
    this.grid.get(obstacleYPos).set(obstacleXPos, "#");
  }
  
  void move() throws LoopException {
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
    
    NodePart2 nextNode = new NodePart2(this.xPos, this.yPos, this.direction);
    
    if(visitedNodes.contains(nextNode)) {
      throw new LoopException("Loop detected!");
    }
    
    this.visitedNodes.add(nextNode);
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

@EqualsAndHashCode
class NodePart2 {
  int x,y;
  Direction direction;
  
  NodePart2(int x, int y, Direction direction) {
    this.x = x;
    this.y = y;
    this.direction = direction;
  }
}

class LoopException extends Exception {
  LoopException(String message) {
    super(message);
  }
}

