# aoc

Fast and idiomatic Kotlin solutions for [Advent of Code][aoc] puzzles from 2021-2025.

## Usage

Input files are expected in `data/YYYY/DD.txt`. 
For instance:
- `data/2021/3.txt`
- `data/2024/21.txt`

To run a specific day:
```shell
./gradlew run -Pyear=2024 -Pday=21
```

To test a specific year:
```shell
./gradlew test --tests year2024.Test2024
```

[aoc]: https://adventofcode.com