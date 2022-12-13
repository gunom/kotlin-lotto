package lotto.service

import camp.nextstep.edu.missionutils.Console
import lotto.domain.Lotto
import lotto.domain.Prize
import kotlin.system.exitProcess

class GameService {
    fun getWinningNumber(): List<Int> {
        val winningInput = Console.readLine()
        return validate(winningInput)
    }

    fun getBonusNumber(winningNumber: List<Int>): Int {
        val bonusNumberInput = Console.readLine()
        return validateBonusNumber(bonusNumberInput, winningNumber)
    }

    fun getGameResult(
        lottoList: List<Lotto>,
        winningNumber: List<Int>,
        bonusNumber: Int,
    ): List<Int> {
        val matcher = winningNumber.toMutableSet()
        matcher.add(bonusNumber)
        val gameResult = lottoList.map {
            val match = it.match(matcher)
            val result = match.size
            if (!match.contains(bonusNumber) && result == 6) result + 1 else result
        }
        return gameResult
    }

    fun getTotalPrize(gameResult: List<Int>): Long {
        val totalPrize = gameResult.sumOf {
            Prize.getReword(it)
        }
        return totalPrize
    }

    private fun validate(winningInput: String): List<Int> {
        try {
            val winningNumber = winningInput.split(",").map { it.toInt() }
            //validate win number
            if (winningNumber.distinct() != winningNumber) throw IllegalArgumentException("[ERROR] 당첨 번호는 중복되지 않아야 합니다.")
            if (winningNumber.filter { it in 1..45 } != winningNumber) throw IllegalArgumentException("[ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.")
            if (winningNumber.size != 6) throw IllegalArgumentException("[ERROR] 당첨 번호는 6자리여야 합니다.")
            return winningNumber
        } catch (exception: NumberFormatException) {
            println("[ERROR] 당첨 번호는 숫자여야 합니다.")
            exitProcess(1)
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            exitProcess(1)
        }
    }

    private fun validateBonusNumber(
        bonusNumberInput: String,
        winningNumber: List<Int>
    ): Int {
        try {
            val bonusNumber = bonusNumberInput.toInt()
            if (bonusNumber !in 1..45) throw IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.")
            if (winningNumber.contains(bonusNumber)) throw IllegalArgumentException("[ERROR] 보너스 번호는 당첨번호와 중복될 수 없습니다.")
            return bonusNumber
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            exitProcess(1)
        } catch (exception: NumberFormatException) {
            println("[ERROR] 보너스 번호는 숫자여야 합니다.")
            exitProcess(1)
        }
    }
}