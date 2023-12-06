package day2

import "testing"

func TestNewGame(t *testing.T) {
  tests := []struct {
    game string
    want Game
  }{
    {
      game: "Game 10: 1 green, 5 blue, 3 red; 4 green, 9 red, 3 blue; 11 red, 2 green, 5 blue; 3 green, 1 blue, 2 red; 6 red, 2 blue",
      want: Game{
        gameNumber: 10,
        peeks: []Peek{
          {3, 1, 5},
          {9, 3, 4},
          {11, 2, 5},
          {2, 3, 1},
          {6, 0, 2},
        },
      },
    },
  }
  for _, tt := range tests {
    t.Run(tt.game, func(t *testing.T) {
      if got := NewGame(tt.game); got.gameNumber != tt.want.gameNumber {
        t.Errorf("NewGame(%s) = %d, want %d", tt.game, got, tt.want)
      }
    })
  }
}

func TestIsGamePlayable(t *testing.T) {
  tests := []struct {
    name string
    game Game
    want bool
  }{
    {
      name: "Game is playable",
      game: Game{
        gameNumber: 10,
        peeks: []Peek{
          {3, 1, 5},
          {9, 3, 4},
          {11, 2, 5},
          {2, 3, 1},
          {6, 0, 2},
        },
        limitRed:   12,
        limitGreen: 13,
        limitBlue:  14,
      },
      want: true,
    },
    {
      name: "Game is NOT playable",
      game: Game{
        gameNumber: 10,
        peeks: []Peek{
          {3, 1, 5},
          {9, 3, 4},
          {11, 2, 5},
          {2, 3, 1},
          {6, 0, 100},
        },
        limitRed:   12,
        limitGreen: 13,
        limitBlue:  14,
      },
      want: false,
    },
  }
  for _, tt := range tests {
    t.Run(tt.name, func(t *testing.T) {
      if got := tt.game.IsGamePlayable(); got != tt.want {
        t.Errorf("%s - TestIsGamePlayable() = %t, want %t", tt.name, got, tt.want)
      }
    })
  }
}

func TestGetSumOfIdsOfPlayableGames(t *testing.T) {
  tests := []struct {
    file string
    want int
  }{
    {
      file: "sample.txt",
      want: 8,
    },
    {
      file: "whole.txt",
      want: 2162,
    },
  }
  for _, tt := range tests {
    t.Run(tt.file, func(t *testing.T) {
      if got := GetSumOfIdsOfPlayableGames(tt.file); got != tt.want {
        t.Errorf("TestGetSumOfIdsOfPlayableGames(%s) = %d, want %d", tt.file, got, tt.want)
      }
    })
  }
}

func TestGetSumOfMinimumPowersFromAllGames(t *testing.T) {
  tests := []struct {
    file string
    want int
  }{
    {
      file: "sample.txt",
      want: 2286,
    },
		{
      file: "whole.txt",
      want: 2286,
    },
  }
  for _, tt := range tests {
    t.Run(tt.file, func(t *testing.T) {
      if got := GetSumOfMinimumPowersFromAllGames(tt.file); got != tt.want {
        t.Errorf("TestGetSumOfMinimumPowersFromAllGames(%s) = %d, want %d", tt.file, got, tt.want)
      }
    })
  }
}