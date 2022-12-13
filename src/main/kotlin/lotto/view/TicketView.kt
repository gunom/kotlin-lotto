package lotto.view

class TicketView(
    private val input: Long,
) : View {
    private val message: String = "%d개를 구매했습니다."
    override fun display() {
        println(String.format(message, input))
    }
}