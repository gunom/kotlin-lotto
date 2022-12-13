package lotto.service

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms
import lotto.domain.Lotto
import kotlin.system.exitProcess

class UserService {
    fun putMoney(): Long {
        return validate(Console.readLine())
    }

    fun getLotto(money: Long): MutableList<Lotto> {
        val ticket = money.div(1000)
        val lottoList = mutableListOf<Lotto>()
        for (i in 1..ticket) {
            val numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
            lottoList.add(Lotto(numbers))
        }
        return lottoList
    }

    fun getPortfolio(money: Long, prize: Long): Double {
        return prize.toDouble().div(money) * 100
    }

    private fun validate(moneyInput: String): Long {
        try {
            val money = moneyInput.toLong()
            if (money.mod(1000) > 0) {
                throw IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.")
            }
            return money
        } catch (exception: NumberFormatException) {
            println("[ERROR] 구입 금액은 숫자여야 합니다.")
            exitProcess(1)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            exitProcess(1)
        }
    }
}