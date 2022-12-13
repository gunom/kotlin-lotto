package lotto.`interface`

import lotto.application.LottoFacade

class LottoController {
    private val lottoFacade = LottoFacade()

    fun run() {
        lottoFacade.startGame()
    }
}