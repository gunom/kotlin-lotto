package lotto.view

import lotto.domain.Prize

class GameResultView(
    private val gameResult: List<Int>
) : View {
    private val message = "당첨 통계\n" +
            "---\n" +
            "3개 일치 (5,000원) - %d개\n" +
            "4개 일치 (50,000원) - %d개\n" +
            "5개 일치 (1,500,000원) - %d개\n" +
            "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n" +
            "6개 일치 (2,000,000,000원) - %d개"

    override fun display() {
        val moreFrequencies = gameResult.groupingBy { it }.eachCount()
        val fifth = moreFrequencies[Prize.FIFTH_PRIZE.number] ?: 0
        val forth = moreFrequencies[Prize.FORTH_PRIZE.number] ?: 0
        val third = moreFrequencies[Prize.THIRD_PRIZE.number] ?: 0
        val second = moreFrequencies[Prize.SECOND_PRIZE.number] ?: 0
        val first = moreFrequencies[Prize.FIRST_PRIZE.number] ?: 0
        val array = listOf(fifth, forth, third, second, first).toTypedArray()
        println(String.format(message, *array))
    }
}