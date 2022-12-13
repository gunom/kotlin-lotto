package lotto.domain

class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == 6)
        require(numbers.distinct() == numbers)
    }

    fun display() {
        println(numbers)
    }

    fun match(matcher: Set<Int>): Set<Int> {
        return numbers.toSet().intersect(matcher)
    }
}
