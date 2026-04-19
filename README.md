# aoc

Kotlin solutions for [Advent of Code][aoc] puzzles.

## Usage

Place your input files in `input/yearYYYY/DayDD.txt`. For example:
- `input/year2021/Day03.txt`
- `input/year2024/Day21.txt`

Run a specific day:
```
./gradlew run --args year2024.Day21
```

Test a specific day:
```
./gradlew test --tests year2024.Day21Test
```

Test a specific year:
```
./gradlew test --tests year2024*
```

[aoc]: https://adventofcode.com