import isel.leic.UsbPort

object HAL {
    private val port = UsbPort.read()

    fun init() = println("HAL started")

    fun isBit(mask: Int): Boolean{
        val bin = port and mask
        return bin == mask
    }

    fun readBits(mask: Int): Int = port and mask

    fun writeBits(mask: Int, value: Int) = UsbPort.write((port and mask) and value)

    fun setBits(mask: Int) = UsbPort.write(port or mask)

    fun clrBits(mask: Int) = UsbPort.write(port xor mask)
}