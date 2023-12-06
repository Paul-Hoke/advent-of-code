package day2

import (
  "bufio"
  "io"
  "os"
  "regexp"
  "strconv"
  "strings"
  "slices"
)

type Game struct {
  gameNumber int
  peeks      []Peek
  limitRed   int
  limitGreen int
  limitBlue  int
}

type Peek struct {
  numRed   int
  numGreen int
  numBlue  int
}

func NewGame(input string) Game {

  peeks := []Peek{}

  gameNumber := ExtractRegexValue(input, `Game (?P<Value>[0-9]+)`)

  peekParts := strings.Split(input, ";")

  for _, v := range peekParts {
    numRed := ExtractRegexValue(v, `(?P<Value>[0-9]+) red`)
    numGreen := ExtractRegexValue(v, `(?P<Value>[0-9]+) green`)
    numBlue := ExtractRegexValue(v, `(?P<Value>[0-9]+) blue`)
    peeks = append(peeks, Peek{numRed, numGreen, numBlue})
  }

  return Game{
    gameNumber: gameNumber,
    peeks:      peeks,
    limitRed:   12,
    limitGreen: 13,
    limitBlue:  14,
  }
}

func ExtractRegexValue(input, regex string) int {
  re := regexp.MustCompile(regex)
  matches := re.FindStringSubmatch(input)
  index := re.SubexpIndex("Value")

  if len(matches) <= 0 {
    return 0
  }

  value, error := strconv.Atoi(matches[index])

  if error != nil {
    return 0
  }

  return value
}

func (game Game) IsGamePlayable() bool {
  for _, v := range game.peeks {
    if v.numRed > game.limitRed || v.numGreen > game.limitGreen || v.numBlue > game.limitBlue {
      return false
    }
  }
  return true
}

func (game Game) GetPowerFromMinimums() int {
  
  reds := []int{}
  greens := []int{}
  blues := []int{}
  
  for _, v := range game.peeks {
    reds = append(reds, v.numRed)
    greens = append(greens, v.numGreen)
    blues = append(blues, v.numBlue)
  }
  slices.Sort(reds)
  slices.Sort(greens)
  slices.Sort(blues)
  
  return reds[len(reds)-1] * greens[len(greens)-1] * blues[len(blues)-1]
}

//This will give the answer for Part 1 when whole.txt is used
func GetSumOfIdsOfPlayableGames(fileName string) int {

  result := 0

  file, err := os.Open(fileName)
  if err != nil {
    panic(err)
  }

  defer file.Close()

  reader := bufio.NewReader(file)

  for {
    line, _, err := reader.ReadLine()
    if err == io.EOF {
      break
    }
    game := NewGame(string(line))
    if game.IsGamePlayable() {
      result += game.gameNumber
    }
  }

  return result

}

//This will give the answer for Part 1 when whole.txt is used
func GetSumOfMinimumPowersFromAllGames(fileName string) int {

  result := 0

  file, err := os.Open(fileName)
  if err != nil {
    panic(err)
  }

  defer file.Close()

  reader := bufio.NewReader(file)

  for {
    line, _, err := reader.ReadLine()
    if err == io.EOF {
      break
    }
    game := NewGame(string(line))
    result += game.GetPowerFromMinimums()
  }

  return result

}