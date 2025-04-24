import aoc_2024
import gleeunit
import gleeunit/should

pub fn main() -> Nil {
  gleeunit.main()
}

pub fn parse_input_test() {
  aoc_2024.parse_input("123   456\n111   222\n")
  |> should.equal([#(123, 456), #(111, 222)])
}
