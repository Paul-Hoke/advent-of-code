package day4

import "testing"
import "reflect"

func TestNewCard(t *testing.T) {
  tests := []struct {
    input string
    want Card
  }{
    {
      input: "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
      want: Card {
        myNumbers: []int{41,48,83,86,17},
        winningNumbers: []int{83,86,6,31,17,9,48,53},
      },
    },
  }
  for _, tt := range tests {
    t.Run(tt.input, func(t *testing.T) {
      if got := NewCard(tt.input); !reflect.DeepEqual(got, tt.want) {
        t.Errorf("NewGame(%s) = %d, want %d", tt.input, got, tt.want)
      }
    })
  }
}

func TestGetPoints(t *testing.T) {
  tests := []struct {
    input string
    want int
  }{
    {
      input: "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
      want: 8,
    },
    {
      input: "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
      want: 2,
    },
    {
      input: "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
      want: 2,
    },
    {
      input: "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
      want: 1,
    },
    {
      input: "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
      want: 0,
    },
    {
      input: "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
      want: 0,
    },
  }
  for _, tt := range tests {
    t.Run(tt.input, func(t *testing.T) {
      if got := NewCard(tt.input); got.GetPoints() != tt.want {
        t.Errorf("GetPoints(%s) = %d, want %d", tt.input, got, tt.want)
      }
    })
  }
}

func TestGetSumOfPointsFromAllCards(t *testing.T) {
  tests := []struct {
    file string
    want int
  }{
    {
      file: "sample.txt",
      want: 13,
    },
    {
      file: "whole.txt",
      want: 999,
    },
  }
  for _, tt := range tests {
    t.Run(tt.file, func(t *testing.T) {
      if got := GetSumOfPointsFromAllCards(tt.file); got != tt.want {
        t.Errorf("GetSumOfPointsFromAllCards(%s) = %d, want %d", tt.file, got, tt.want)
      }
    })
  }
}