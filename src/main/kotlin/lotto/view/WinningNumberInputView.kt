package lotto.view

class WinningNumberInputView(
) : View {
    private val message: String = "당첨 번호를 입력해 주세요."
    override fun display() {
        println(message)
    }
}