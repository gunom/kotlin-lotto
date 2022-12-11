package lotto

enum class Prize(val number: Int, val reward: Long) {
    FIRST_PRIZE(number = 7, reward = 2000000000),
    SECOND_PRIZE(number = 6, reward = 30000000),
    THIRD_PRIZE(number = 5, reward = 1500000),
    FORTH_PRIZE(number = 4, reward = 50000),
    FIFTH_PRIZE(number = 3, reward = 5000);

    companion object {
        fun getReword(number: Int): Long {
            return values().find { it.number == number }?.reward ?: 0
        }
    }
}
