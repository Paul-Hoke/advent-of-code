package day4

import "strings"
import "strconv"
import "slices"
import "os"
import "bufio"
import "io"

type Card struct {
  myNumbers []int
  winningNumbers []int
}

func NewCard(input string) Card {
  
  myNumbers := []int{}
  winningNumbers := []int{}
  
  cardTable := strings.TrimSpace(strings.Split(input, ":")[1])
  cardTableParts := strings.Split(cardTable, "|")
  
  myNumberParts := strings.Split(string(cardTableParts[0]), " ")
  winningNumberParts := strings.Split(string(cardTableParts[1]), " ")
  
  for _, v := range myNumberParts {
    myNumber, error := strconv.Atoi(v)
    if v != "" && error == nil {
      myNumbers = append(myNumbers, myNumber)
    }
  }
  
  for _, v := range winningNumberParts {
    winningNumber, error := strconv.Atoi(v)
    if v != "" && error == nil {
      winningNumbers = append(winningNumbers, winningNumber)
    }
  }
  
  return Card {
    myNumbers: myNumbers,
    winningNumbers: winningNumbers,
  }
}

func (card Card) GetPoints() int {
  
  points := 0
  
  for _, v := range card.myNumbers {
    if slices.Contains(card.winningNumbers, v) {
      if points == 0 {
        points += 1
      } else {
        points *= 2
      }
    }
  }
  return points
}

func GetSumOfPointsFromAllCards(fileName string) int {
  
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
    card := NewCard(string(line))
    result += card.GetPoints()
  }

  return result
  
}