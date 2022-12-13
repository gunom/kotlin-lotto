package lotto.view

class InputMoneyView(
) : View {
    private val message: String = "구매금액을 입력하세요."
    override fun display() {
        return println(message)
    }
}