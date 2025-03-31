import isel.leic.UsbPort
import isel.leic.utils.*

object LCD {
    private const val FUNCTIONSET_TO_8BIT = 0x03
    private const val FUNCTIONSET_TO_4BIT = 0x02
    private const val FUNCTIONSET_2LINES = 0x28
    private const val DISPLAY_OFF = 0x08
    private const val CLEAR_DISPLAY = 0x01
    private const val ENTRY_MODE_SET = 0x06
    private const val DISPLAY_ON = 0x0F

    private const val LINES = 2
    private const val COLS = 16
    private const val SERIAL_INTERFACE = false

    private const val RS_MASK = 0x10
    private const val E_MASK = 0x20
    private const val DATA_MASK = 0x0F

    private fun writeNibbleParallel(rs: Boolean, data: Int) {
        if (rs) HAL.setBits(RS_MASK) else HAL.clrBits(RS_MASK)
        Time.sleep(1)
        HAL.setBits(E_MASK)
        Time.sleep(1)
        HAL.writeBits(DATA_MASK, data)
        Time.sleep(1)
        HAL.clrBits(E_MASK)
    }

    private fun writeNibbleSerial(rs: Boolean, data: Int){}

    private fun writeNibble(rs: Boolean, data: Int){
        if (SERIAL_INTERFACE) writeNibbleSerial(rs,data)
        else writeNibbleParallel(rs, data)
    }

    private fun writeByte(rs: Boolean, data: Int){
        writeNibble(rs, data.ushr(4))
        writeNibble(rs, data)
        Time.sleep(10)
    }

    private fun writeCMD(data: Int){
        writeByte(false, data)
    }

    private fun writeDATA(data: Int){
        writeByte(true, data)
    }

    fun init(){
        Time.sleep(15)
        writeNibble(false, FUNCTIONSET_TO_8BIT)
        Time.sleep(5)
        writeNibble(false, FUNCTIONSET_TO_8BIT)
        Time.sleep(1)
        writeNibble(false, FUNCTIONSET_TO_8BIT)
        writeNibble(false, FUNCTIONSET_TO_4BIT)
        writeCMD(FUNCTIONSET_2LINES)
        writeCMD(DISPLAY_OFF)
        writeCMD(CLEAR_DISPLAY)
        writeCMD(ENTRY_MODE_SET)
        writeCMD(DISPLAY_ON)
    }

    fun write(c: Char){
        writeDATA(c.hashCode())
        Time.sleep(2)
    }

    fun write(text: String){
        for (i in 0..<text.toCharArray().size) write(text[i])
    }

    fun cursor(line: Int, column: Int){
        val jump = 128 or line.shl(6) or column
        writeCMD(jump)
    }

    fun clear(){
        writeCMD(CLEAR_DISPLAY)
    }
}

fun main() {
    HAL.init()
    KBD.init()
    LCD.init()
    LCD.write("Hello, world!")
    Time.sleep(2000)
    LCD.clear()
    LCD.write("Roulette Game")
    LCD.cursor(1, 0)
    LCD.write("ISEL-LIC SV2425")
}