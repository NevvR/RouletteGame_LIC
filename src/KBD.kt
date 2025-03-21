import isel.leic.UsbPort.*

object KBD {
    const val NONE = 0
    private const val GET_ACK = 0x80
    private const val GET_VAL = 0x80
    private const val GET_BITS = 0x0F

    fun init() {
        println("KBD initiated.")
        write(NONE)
    }

    fun getKey(): Char {
        if (HAL.isBit(GET_VAL)){
            HAL.setBits(GET_ACK)
            return HAL.readBits(GET_BITS).toChar()
        } else return NONE.toChar()
    }

    fun waitKey(timeout: Long): Char {
        return 'c'
    }
}