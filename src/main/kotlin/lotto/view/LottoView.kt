package lotto.view

import lotto.domain.Lotto

class LottoView(
    private val input: List<Lotto>,
) : View{
    override fun display(){
        input.map { it.display() }
    }
}