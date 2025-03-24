import isel.leic.UsbPort.*

object HAL {
    var output: Int = 0
    var clock = 0

    fun init() {
        println("HAL started")
        write(output)
    }

    fun isBit(mask: Int): Boolean = (read() and mask) > 0

    fun readBits(mask: Int): Int = output and mask

    fun writeBits(mask: Int, value: Int) {
        output = output or (mask and value)
        write(output)
    }

    fun setBits(mask: Int) {
        output = output or mask
        write(output)
    }

    fun clrBits(mask: Int) {
        output = output and mask.inv()
        write(output)
    }
}

fun main() {

}