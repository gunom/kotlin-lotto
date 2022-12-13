package lotto.service

import lotto.domain.Lotto
import lotto.view.*

class ViewService {
    fun showMoneyInputView() {
        InputMoneyView().display()
    }

    fun showLottoListView(lottoList: List<Lotto>) {
        LottoView(input = lottoList).display()
    }

    fun showTicketView(ticket: Long) {
        TicketView(input = ticket).display()
    }

    fun showWinningNumberInputView() {
        WinningNumberInputView().display()
    }

    fun showBonusNumberInputView() {
        BonusNumberInputView().display()
    }

    fun showGameResultView(gameResult: List<Int>) {
        GameResultView(gameResult).display()
    }

    fun showPortfolioView(portfolio: Double) {
        PortfolioView(portfolio).display()
    }
}