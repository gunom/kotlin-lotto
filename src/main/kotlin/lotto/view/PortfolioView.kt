package lotto.view

class PortfolioView(
    private val portfolio: Double
):View {
    private val message = "총 수익률은 %.2f%s입니다."
    override fun display() {
        println(String.format(message, portfolio, "%"))
    }
}