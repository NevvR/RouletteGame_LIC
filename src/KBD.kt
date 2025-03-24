import isel.leic.UsbPort.*
import isel.leic.utils.*

private const val GET_ACK = 0x80
private const val GET_VAL = 0x80
private const val GET_BITS = 0x0F

private val ARRAY = arrayOf('1','4','7','*','2','5','8','0','3','6','9','#','A','B','C','D')

object KBD {
    private const val NONE = 0

    fun init() {
        println("KBD initiated.")
        //HAL.init()
    }

    fun getKey(): Char {
        if (HAL.isBit(GET_VAL)){
            val key = ARRAY[HAL.readBits(GET_BITS) and GET_BITS]
            HAL.setBits(GET_ACK)
            while (HAL.isBit(GET_VAL)) HAL.clrBits(GET_ACK)
            return key
        } else return NONE.toChar()
    }

    fun waitKey(timeout: Long): Char {
        val startTime = Time.getTimeInMillis()
        while (Time.getTimeInMillis() - startTime < timeout){
            val key = getKey()
            if (key != NONE.toChar()) return key
        }
        return NONE.toChar()
    }
}

fun main() {
    HAL.init()
    KBD.init()
    HAL.setBits(0x05)
    KBD.getKey()
}