# aoc

Fast and idiomatic Kotlin solutions for [Advent of Code][aoc] puzzles from 2021-2025.

## Usage

Input files are expected in `data/YYYY/DD.txt`. 
For instance:
- `data/2021/3.txt`
- `data/2024/21.txt`

To run a specific day:
```shell
./gradlew run --args year2024.Day21
```

To test a specific year:
```shell
./gradlew test --tests year2024.Test2024
```

To run a specific benchmark:
```shell
./gradlew benchmark -Pinclude=".*2024.*day22.*"
```

[aoc]: https://adventofcode.com