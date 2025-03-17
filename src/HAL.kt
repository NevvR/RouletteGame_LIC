import isel.leic.UsbPort.*

object HAL {
    val leds: Int = 0b1011_0010

    fun init() {
        println("HAL started")
        write(leds)
    }

    fun isBit(mask: Int): Boolean = (read() and mask) > 0

    fun readBits(mask: Int): Int = read() and mask

    fun writeBits(mask: Int, value: Int) = write((read() and mask) and value)

    fun setBits(mask: Int) = write(read() or mask)

    fun clrBits(mask: Int) = write(read() xor mask)
}