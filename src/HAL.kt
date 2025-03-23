import isel.leic.UsbPort.*

object HAL {
    var leds: Int = 0
    var clock = 0

    fun init() {
        println("HAL started")
        write(leds)
    }

    fun isBit(mask: Int): Boolean = (read() and mask) > 0

    fun readBits(mask: Int): Int = leds and mask

    fun writeBits(mask: Int, value: Int) {
        leds = leds or (mask and value)
        write(leds)
    }

    fun setBits(mask: Int) {
        leds = leds or mask
        write(leds)
    }

    fun clrBits(mask: Int) {
        leds = leds and mask.inv()
        write(leds)
    }
}