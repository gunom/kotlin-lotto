package lotto.application

import lotto.service.ViewService
import lotto.service.GameService
import lotto.service.UserService

class LottoFacade {
    private val TICKET_PRICE = 1000
    private val viewService = ViewService()
    private val userService = UserService()
    private val gameService = GameService()

    fun startGame(){
        viewService.showMoneyInputView()
        val money = userService.buyLotto()
        val lottoList = userService.getLotto(money)
        val ticket = money.div(TICKET_PRICE)
        viewService.showTicketView(ticket)
        viewService.showLottoListView(lottoList)

        viewService.showWinningNumberInputView()
        val winningNumber = gameService.getWinningNumber()
        viewService.showBonusNumberInputView()
        val bonusNumber = gameService.getBonusNumber(winningNumber)

        val gameResult = gameService.getGameResult(lottoList, winningNumber, bonusNumber)
        val totalPrize = gameService.getTotalPrize(gameResult)
        val portfolio = userService.getPortfolio(money, totalPrize)
        viewService.showGameResultView(gameResult)
        viewService.showPortfolioView(portfolio)
    }
}