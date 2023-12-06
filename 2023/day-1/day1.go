package day1

import (
  "bufio"
  "io"
  "os"
  "strconv"
  "strings"
)

func GetIntFromFirstAndLast(input string)  int {
  first := ""
  last := ""
  
  //Get the first match
  for _, v := range input {
    if _, err := strconv.Atoi(string(v)); err == nil {
      first = string(v)
      break
    }
  }
  
  //Get the last match
  for i := len(input)-1; i >= 0; i-- {
    if _, err := strconv.Atoi(string(input[i])); err == nil {
      last = string(input[i])
      break
    }
  }
  
  result, _ := strconv.Atoi(first + last)
  
  return result
}

func GetResultFromFile(fileName string) int {
  
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
    result += GetIntFromFirstAndLast(MungeInput(string(line)))
  }
  
  return result
  
}

func MungeInput(input string) string {
  input = strings.ReplaceAll(input, "one", "o1ne")
  input = strings.ReplaceAll(input, "two", "t2wo")
  input = strings.ReplaceAll(input, "three", "t3hree")
  input = strings.ReplaceAll(input, "four", "f4our")
  input = strings.ReplaceAll(input, "five", "f5ive")
  input = strings.ReplaceAll(input, "six", "s6ix")
  input = strings.ReplaceAll(input, "seven", "s7even")
  input = strings.ReplaceAll(input, "eight", "e8ight")
  input = strings.ReplaceAll(input, "nine", "n9ine")
  
  return input
}