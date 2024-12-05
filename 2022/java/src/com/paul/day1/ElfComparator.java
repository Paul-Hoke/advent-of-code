package com.paul.day1;

import java.util.Comparator;

public class ElfComparator implements Comparator<Elf> {
  
  @Override
  public int compare(Elf left, Elf right) {
    return left.totalCalories - right.totalCalories;
  }
}
