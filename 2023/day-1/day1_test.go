package day1

import "testing"

func TestHello(t *testing.T) {
    want := "Hello, world."
    if got := Hello(); got != want {
        t.Errorf("Hello() = %q, want %q", got, want)
    }
}

func TestGetIntFromFirstAndLast(t *testing.T) {
  tests := []struct {
		input string
		want int
	}{
		{
			input: "1abc2",
			want: 12,
		},
    {
			input: "pqr3stu8vwx",
			want: 38,
		},
    {
			input: "a1b2c3d4e5f",
			want: 15,
		},
    {
			input: "treb7uchet",
    want: 77,
		},
  }
  for _, tt := range tests {
		t.Run(tt.input, func(t *testing.T) {
			if got := GetIntFromFirstAndLast(tt.input); got != tt.want {
				t.Errorf("GetIntFromFirstAndLast(%s) = %d, want %d", tt.input, got, tt.want)
			}
		})
	}
}

func TestGetResultFromFile(t *testing.T) {
  tests := []struct {
		file string
		want int
	}{
		{
			file: "sample.txt",
			want: 142,
		},
    {
			file: "sample2.txt",
			want: 281,
		},
    {
			file: "whole.txt",
			want: 53866,
		},
  }
  for _, tt := range tests {
		t.Run(tt.file, func(t *testing.T) {
			if got := GetResultFromFile(tt.file); got != tt.want {
				t.Errorf("GetResultFromFile(%s) = %d, want %d", tt.file, got, tt.want)
			}
		})
	}
}
