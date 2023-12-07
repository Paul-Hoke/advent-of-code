package day4

import "strings"
import "strconv"
import "slices"
import "os"
import "bufio"
import "io"
import day2 "hoke.paul/advent-of-code/2023/day-2"

type Card struct {
  cardNumber int
  myNumbers []int
  winningNumbers []int
  count int
}

func NewCard(input string) Card {
  
  cardNumber := day2.ExtractRegexValue(input, `Card (?P<Value>[0-9]+)`)
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
    cardNumber: cardNumber,
    myNumbers: myNumbers,
    winningNumbers: winningNumbers,
    count: 1,
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

func (card Card) GetNumWinners() int {
  
  numWinners := 0
  
  for _, v := range card.myNumbers {
    if slices.Contains(card.winningNumbers, v) {
     numWinners++
    }
  }
  return numWinners
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

func GetSumOAllProcessedCards(fileName string) int {
  
  result := 0
  allCards := []Card{}
  //allCards = make(map[int]Card)
  
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
    allCards = append(allCards, card)
  }
  
  for i, value := range allCards {
    for j := 1; j <= value.GetNumWinners(); j++ {
      currentCard := allCards[i + j]
      currentCard.count += value.count
      allCards[i + j] = currentCard
    }
  }
  
  for _, value := range allCards {
    result += value.count
  }
  
  return result
}