package util.core

/**
 * Returns 6th *element* from the list.
 *
 * Throws an [IndexOutOfBoundsException] if the size of this list is less than 5.
 */
operator fun <T> List<T>.component6() = get(5)