package util

private val ASCII_ART_LETTERS = intArrayOf(
    0x699f99, 0xe9e99e, 0x698896, 0xe9999e, 0xf8e88f, 0xf8e888, 0x698b97, 0x99f999,
    0xf2222f, 0x311196, 0x9aca99, 0x88888f, 0x9ff999, 0x9db999, 0x699996, 0xe99e88,
    0x6999b7, 0xe99ea9, 0x786196, 0xf22222, 0x999996, 0x999962, 0x999ff9, 0x996699,
    0x996222, 0xf1248f
)

/**
 * Converts AoC "ASCII-Art" letters into corresponding characters and provides the resulting string.
 *
 * For example
 * ```
 * .##..###...##.
 * #..#.#..#.#..#
 * #..#.###..#...
 * ####.#..#.#...
 * #..#.#..#.#..#
 * #..#.###...##.
 * ```
 * will be converted into `ABC`.
 *
 * @return the converted [String] representation of this string's ASCII art.
 */
fun String.fromAsciiArt(): String {
    val glyphs = ASCII_ART_LETTERS.mapIndexed { i, g -> g to 'A' + i }.toMap()
    val rows = this.lines()
    val w = 5
    val charCount = (rows[0].length + 1) / w
    return buildString {
        for (c in 0..<charCount) {
            var glyph = 0
            for (i in 0..<rows.size) {
                for (j in 0..<w) {
                    val x = c * w + j
                    if (rows[i][x] == '#') {
                        val idx = (5 - i) * 4 + (3 - j)
                        glyph = glyph or (1 shl idx)
                    }
                }
            }
            append(glyphs[glyph] ?: '?')
        }
    }
}