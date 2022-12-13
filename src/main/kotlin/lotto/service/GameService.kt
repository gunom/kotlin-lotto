package lotto.service

import camp.nextstep.edu.missionutils.Console
import lotto.common.ExceptionMessage
import lotto.common.ExceptionMessage.*
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
            if (winningNumber.distinct() != winningNumber) throw IllegalArgumentException(
                DUPLICATE_WINNING_NUMBER_EXCEPTION.message)
            if (winningNumber.filter { it in 1..45 } != winningNumber) throw IllegalArgumentException(WINNING_NUMBER_BOUNDARY_EXCEPTION.message)
            if (winningNumber.size != 6) throw IllegalArgumentException(WINNING_NUMBER_LENGTH_EXCEPTION.message)
            return winningNumber
        } catch (exception: NumberFormatException) {
            println(WINNING_NUMBER_FORMAT_EXCEPTION.message)
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
            if (bonusNumber !in 1..45) throw IllegalArgumentException(NUMBER_BOUNDARY_EXCEPTION.message)
            if (winningNumber.contains(bonusNumber)) throw IllegalArgumentException(
                DUPLICATE_WITH_WINNING_NUMBER_EXCEPTION.message)
            return bonusNumber
        } catch (exception: IllegalArgumentException) {
            println(exception.message)
            exitProcess(1)
        } catch (exception: NumberFormatException) {
            println(BONUS_NUMBER_FORMAT_EXCEPTION.message)
            exitProcess(1)
        }
    }
}