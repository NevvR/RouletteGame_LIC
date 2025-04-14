import isel.leic.utils.Time
import SerialEmitter.Destination

object SerialEmitter {

    enum class Destination {
        LCD,
        ROULETTE
    }

    fun init() {
        HAL.init()
    }

    fun send(addr: Destination, data: Int, size: Int) {
        val mask = when (addr) {
            Destination.LCD -> 0x01
            Destination.ROULETTE -> 0x02
        }
        HAL.writeBits(mask, data)
        Time.sleep(10)
        HAL.clrBits(mask)
    }
}

fun main() {
    SerialEmitter.init()
    SerialEmitter.send(Destination.LCD,0x15,5)
}