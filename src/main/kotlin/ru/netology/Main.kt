package ru.netology

const val NAME_CARD_VK_PAY = "VK Pay"
const val NAME_CARD_VISA = "Visa"
const val NAME_CARD_MAESTRO = "Maestro"
const val NAME_CARD_MASTERCARD= "Mastercard"
const val NAME_CARD_MIR = "Mir"


fun main() {

    val a = commCalc(1000_00, prevTrans = 17_00)
    message(a,"VK Pay")
    val b = commCalc(105000_00, card = "Visa", prevTrans = 1007_00)
    message(b,"Visa")
    val c = commCalc(1000_00, card = "Maestro", prevTrans = 17525_00)
    message(c,"Maestro")
    val e = commCalc(1000_00, card = "Mir", prevTrans = 1457_00)
    message(e,"Mir")
    val d = commCalc(100300_00, card = "Mastercard", prevTrans = 17000_00)
    message(d,"Mastercard")
}

fun message(
    сommission: Int,
    card: String
) {
    val result = (сommission.toDouble()/100)
    val ruble = сommission/100
    val penny =  ((result - (сommission/100))*100).toInt()

    println("Коммисия за перевод по карте $card равна: $ruble рублей $penny копейк")
}

fun commCalc (
    amount: Int,
    card: String = "VK Pay",
    prevTrans: Int = 0
): Int{
    return when (card){
        NAME_CARD_VK_PAY-> {
            0
        }
        NAME_CARD_MAESTRO,NAME_CARD_MASTERCARD-> {
            val summTransMax = 75000_00
            val сommission = 0.6
            val сommissionFixed = 20_00

            val result = amount + prevTrans

            if (result > summTransMax) {
                return (((amount/100 * сommission)) + сommissionFixed).toInt()
            } else {
                0
            }
        }
        NAME_CARD_VISA,NAME_CARD_MIR->{
            val сommission = 0.75
            val fixedCommission = 3500
            val result = (((amount/100 * сommission))).toInt()
            return if(result>=fixedCommission){
                result
            }else{
                fixedCommission
            }
        }
        else -> {
            error("Нет такой карты")
        }
    }
}

