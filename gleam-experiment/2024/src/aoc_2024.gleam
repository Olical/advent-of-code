import gleam/bool
import gleam/int
import gleam/list
import gleam/pair
import gleam/string
import pprint
import simplifile

pub fn parse_input(input: String) -> List(#(Int, Int)) {
  string.split(input, "\n")
  |> list.filter(fn(line) { bool.negate(string.is_empty(line)) })
  |> list.map(fn(line) {
    case
      list.map(string.split(line, "   "), fn(num_str) {
        let assert Ok(num) = int.parse(num_str)
        num
      })
    {
      [a, b] -> #(a, b)
      _ -> panic as "Invalid input format"
    }
  })
}

pub fn main() -> Nil {
  let assert Ok(content) = simplifile.read("./inputs/day_01.txt")
  let #(left, right) =
    parse_input(content)
    |> list.unzip()
    |> pair.map_first(fn(xs) { list.sort(xs, by: int.compare) })
    |> pair.map_second(fn(xs) { list.sort(xs, by: int.compare) })

  list.map2(left, right, fn(l, r) { int.absolute_value(l - r) })
  |> int.sum()
  |> pprint.debug()

  Nil
}
