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

    fun writeBits(mask: Int, value: Int) {

    }

    fun setBits(mask: Int): Int {
        val bin = port and mask
        return bin or mask
    }


}