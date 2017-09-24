import com.github.sealwing.elephant.mobile.core.Construction
import com.github.sealwing.elephant.mobile.core.ConstructionType
import com.github.sealwing.elephant.mobile.core.Deal
import com.github.sealwing.elephant.mobile.core.ProfileType

// TO STRING

fun fromStructToString(deal: Deal, id: Int = -1): String {
    val stack = if (id > -1) deal.constructionList[id] else deal.constructionList[deal.constructionList.lastIndex]
    val str = StringBuilder("${getRussianTitle(stack.type)} ${stack.width}x${stack.height}, ${stack.amount} шт.:")
    for ((type, length, amount) in stack.profilesList) {
        str.append("\n\t${getRussianTitle(type)} - $length мм., ${amount * stack.amount} шт.")
    }
    str.append(calculateFitting(stack))
    return str.toString()
}

fun calculateFitting(stack: Construction): String =
        when (stack.type) {
            ConstructionType.TRIPLE -> "\n\tЗаполнение ${stack.fitWidth}x${stack.fitHeight} мм. x 2\n\t\t${stack.fitWidth + 8}x${stack.fitHeight} мм. x 1"
            else -> "Заполнение ${stack.fitWidth}x${stack.fitHeight} мм."
        }

// RUSSIAN TITLES

fun getRussianTitle(type: ProfileType): String =
        when (type) {
            ProfileType.INNER -> "Центральная створка"
            ProfileType.OUTER -> "Внешняя створка"
            ProfileType.HORIZONTAL -> "Горизонтальная створка"
            ProfileType.SOLID_FRAME -> "Рама глухаря"
            ProfileType.SOLID_FIT -> "Штапик глухаря"
            ProfileType.HORIZONTAL_FRAME -> "Горизонтальная рама"
            ProfileType.VERTICAL_FRAME -> "Боковая рама"
            ProfileType.STULP -> "Штульп"
        }

fun getRussianTitle(type: ConstructionType): String =
        when (type) {
            ConstructionType.DOUBLE -> "2 створки"
            ConstructionType.TRIPLE -> "3 створки"
            ConstructionType.QUARTET -> "4 створки"
            ConstructionType.SOLID -> "Глухарь"
        }