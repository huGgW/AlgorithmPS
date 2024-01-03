package main

import (
  "fmt"
)

/*
  E: E + T | E - T | T
  T: T * F | T / F | F
  F: (E) | id
*/

type Expr interface {
  PostFix() string
}

type ExprAdd struct {
  Expr
  Left Expr
  Right Term
}
func (e *ExprAdd) PostFix() string {
  return e.Left.PostFix() + e.Right.PostFix() + "+"
}

type ExprMinus ExprAdd
func (e *ExprMinus) PostFix() string {
  return e.Left.PostFix() + e.Right.PostFix() + "-"
}


type Term interface {
  Expr
}

type TermMul struct {
  Term
  Left Term
  Right Factor
}
func (t *TermMul) PostFix() string {
  return t.Left.PostFix() + t.Right.PostFix() + "*"
}

type TermDiv TermMul
func (t *TermDiv) PostFix() string {
  return t.Left.PostFix() + t.Right.PostFix() + "/"
}


type Factor interface {
  Term
}

type FactorParen struct {
  Factor
  E Expr
}
func (f *FactorParen) PostFix() string {
  return f.E.PostFix()
}

type FactorId struct {
  Factor
  Id string
}
func (f *FactorId) PostFix() string {
  return f.Id
}


func createExpr(s string) Expr {
  nested := 0

  for i := len(s)-1; i >= 0; i-- {
    r := s[i]

    if r == ')' {
      nested++
    } else if r == '(' {
      nested--
    } else if nested == 0 && r == '+' {
      return &ExprAdd{Left: createExpr(s[:i]), Right: createTerm(s[i+1:])}
    } else if nested == 0 && r == '-' {
      return &ExprMinus{Left: createExpr(s[:i]), Right: createTerm(s[i+1:])}
    }
  }

  return createTerm(s)
}

func createTerm(s string) Term {
  nested := 0

  for i := len(s)-1; i >= 0; i-- {
    r := s[i]

    if r == ')' {
      nested++
    } else if r == '(' {
      nested--
    } else if nested == 0 && r == '*' {
      return &TermMul{Left: createTerm(s[:i]), Right: createFactor(s[i+1:])}
    } else if nested == 0 && r == '/' {
      return &TermDiv{Left: createTerm(s[:i]), Right: createFactor(s[i+1:])}
    }
  }

  return createFactor(s)
}

func createFactor(s string) Factor {
  if s[0] == '(' {
    if (s[len(s)-1] != ')') {
      panic("Not valid")
    }
    return &FactorParen{E: createExpr(s[1:len(s)-1])}
  } else {
    return &FactorId{Id: s}
  }
}

func main() {
  var line string
  _, err := fmt.Scanln(&line)
  if err != nil { panic(err) }

  e := createExpr(line)
  fmt.Println(e.PostFix())
}