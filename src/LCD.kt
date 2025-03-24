import isel.leic.UsbPort
import isel.leic.utils.*

object LCD {
    private const val LINES = 2
    private const val COLS = 16
    private const val SERIAL_INTERFACE = false

    private const val RS_MASK = 0x20
    private const val E_MASK = 0x10
    private const val DATA_MASK = 0x0F

    private fun writeNibbleParallel(rs: Boolean, data: Int) {
        HAL.setBits(E_MASK)
        if (rs) HAL.setBits(RS_MASK) else HAL.clrBits(RS_MASK)
        HAL.writeBits(DATA_MASK, data)
        HAL.clrBits(E_MASK)
    }

    private fun writeNibbleSerial(rs: Boolean, data: Int){}

    private fun writeNibble(rs: Boolean, data: Int){
        if (SERIAL_INTERFACE) writeNibbleSerial(rs,data)
        else writeNibbleParallel(rs, data)
    }

    private fun writeByte(rs: Boolean, data: Int){
        writeNibble(rs, data.shr(4)) //0x0f
        writeNibble(rs, data) //0xf0
    }

    private fun writeCMD(data: Int){
        writeByte(false, data)
    }

    private fun writeDATA(data: Int){
        writeByte(true, data)
    }

    fun init(){

    }

    fun write(c: Char){
        writeDATA(c.hashCode())
    }

    fun write(text: String){
        for (i in 0..<text.toCharArray().size) write(text[i])
    }

    fun cursor(line: Int, column: Int){

    }

    fun clear(){

    }
}

