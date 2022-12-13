package lotto.common

enum class ExceptionMessage(val message: String) {
    NOT_DIVIDED_MONEY_EXCEPTION("[ERROR] 구입 금액은 1000원 단위여야 합니다."),
    NUMBER_FORMAT_EXCEPTION("[ERROR] 구입 금액은 숫자여야 합니다."),
    NUMBER_BOUNDARY_EXCEPTION("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다."),
    DUPLICATE_WITH_WINNING_NUMBER_EXCEPTION("[ERROR] 보너스 번호는 당첨번호와 중복될 수 없습니다."),
    BONUS_NUMBER_FORMAT_EXCEPTION("[ERROR] 보너스 번호는 숫자여야 합니다."),
    WINNING_NUMBER_FORMAT_EXCEPTION("[ERROR] 당첨 번호는 숫자여야 합니다."),
    DUPLICATE_WINNING_NUMBER_EXCEPTION("[ERROR] 당첨 번호는 중복되지 않아야 합니다."),
    WINNING_NUMBER_BOUNDARY_EXCEPTION("[ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다."),
    WINNING_NUMBER_LENGTH_EXCEPTION("[ERROR] 당첨 번호는 6자리여야 합니다.")
}