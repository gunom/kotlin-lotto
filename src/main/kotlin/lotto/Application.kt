package lotto

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms
import lotto.Prize.*
import kotlin.math.round

fun main() {
    val money: Long
    try {
        println("구입금액을 입력해 주세요.")
        money = Console.readLine().toLong()
        if (money.mod(1000) > 0) {
            throw IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.")
        }
    } catch (exception: NumberFormatException) {
        println("[ERROR] 구입 금액은 숫자여야 합니다.")
        return
    } catch (exception: IllegalArgumentException) {
        println(exception.message)
        return
    }
    val ticket = money.div(1000)
    println("${ticket}개를 구매했습니다.")
    val lottoList = mutableListOf<Lotto>()
    for (i in 1..ticket) {
        val numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6)
        lottoList.add(Lotto(numbers))
    }
    lottoList.map { it.display() }
    println("당첨 번호를 입력해 주세요.")
    val winningNumber: List<Int>
    try {
        val winningInput = Console.readLine()
        winningNumber = winningInput.split(",").map { it.toInt() }
        //validate win number
        if (winningNumber.distinct() != winningNumber) throw IllegalArgumentException("[ERROR] 당첨 번호는 중복되지 않아야 합니다.")
        if (winningNumber.filter { it in 1..45 } != winningNumber) throw IllegalArgumentException("[ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.")
        if (winningNumber.size != 6) throw IllegalArgumentException("[ERROR] 당첨 번호는 6자리여야 합니다.")
    } catch (exception: NumberFormatException) {
        println("[ERROR] 당첨 번호는 숫자여야 합니다.")
        return
    } catch (exception: IllegalArgumentException) {
        println(exception.message)
        return
    }

    println("보너스 번호를 입력해 주세요.")
    val bonusNumber: Int
    try {
        bonusNumber = Console.readLine().toInt()
        if (bonusNumber !in 1..45) throw IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.")
        if(winningNumber.contains(bonusNumber)) throw IllegalArgumentException("[ERROR] 보너스 번호는 당첨번호와 중복될 수 없습니다.")
    } catch (exception: IllegalArgumentException) {
        println(exception.message)
        return
    } catch (exception: NumberFormatException) {
        println("[ERROR] 보너스 번호는 숫자여야 합니다.")
        return
    }
    val matcher = winningNumber.toMutableSet()
    matcher.add(bonusNumber)

    val gameResult = lottoList.map {
        val match = it.match(matcher)
        val result = match.size
        if (!match.contains(bonusNumber) && result == 6) result + 1 else result
    }

    val totalPrize = gameResult.sumOf {
        Prize.getReword(it)
    }

    val portfolio = totalPrize.toDouble().div(money) * 100

    val moreFrequencies = gameResult.groupingBy { it }.eachCount()

    println(
            "당첨 통계\n" +
                    "---\n" +
                    "3개 일치 (5,000원) - ${moreFrequencies[FIFTH_PRIZE.number] ?: 0}개\n" +
                    "4개 일치 (50,000원) - ${moreFrequencies[FORTH_PRIZE.number] ?: 0}개\n" +
                    "5개 일치 (1,500,000원) - ${moreFrequencies[THIRD_PRIZE.number] ?: 0}개\n" +
                    "5개 일치, 보너스 볼 일치 (30,000,000원) - ${moreFrequencies[SECOND_PRIZE.number] ?: 0}개\n" +
                    "6개 일치 (2,000,000,000원) - ${moreFrequencies[FIRST_PRIZE.number] ?: 0}개"
    )

    println("총 수익률은 ${round(portfolio*100) / 100}%입니다.")
}
