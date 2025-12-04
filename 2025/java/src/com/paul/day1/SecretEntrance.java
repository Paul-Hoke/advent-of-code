package com.paul.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SecretEntrance {
  public static void main(String[] args) {
    String filePath = "input/2025/day1/SecretEntrance.txt";
    int dialPosition = 50;
    int timesLandedOnZero = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) continue;

        char direction = line.charAt(0);
        int distance = Integer.parseInt(line.substring(1));

        if (direction == 'L') {
          dialPosition = (dialPosition - distance) % 100;
          if (dialPosition < 0) {
            dialPosition += 100;
          }
        } else if (direction == 'R') {
          dialPosition = (dialPosition + distance) % 100;
        }

        if (dialPosition == 0) {
          timesLandedOnZero++;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("Password: " + timesLandedOnZero);
  }
}
