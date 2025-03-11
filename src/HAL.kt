import isel.leic.UsbPort

object HAL {
    private val port = UsbPort.read()

    fun init(){}

    fun isBit(mask: Int): Boolean{
        val bin = port and mask
        return bin == mask
    }

    fun readBits(mask: Int): Int {
        return port and mask
    }

    fun writeBits(mask: Int, value: Int): Int {
        return (port and mask) and value
    }

    fun setBits(mask: Int): Int {
        return port and mask
    }
    fun clrBits(mask: Int): Int {
        return port xor mask
    }

}