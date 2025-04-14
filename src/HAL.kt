import isel.leic.UsbPort.*

object HAL {
    private var output: Int = 0

    fun init() {
        println("HAL started")
        write(output)
    }

    fun isBit(mask: Int): Boolean = (read() and mask) > 0

    fun readBits(mask: Int): Int = read() and mask

    fun writeBits(mask: Int, value: Int) {
        clrBits(mask)
        setBits(mask and value)
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
    HAL.init()
    while (true) {
        println(HAL.readBits(0x0F))
    }
}