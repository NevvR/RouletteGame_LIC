import isel.leic.UsbPort

object LCD {
    private const val LINES = 2
    private const val COLS = 16

    private fun writeNibbleParallel(rs: Boolean, data: Int){}

    private fun writeNibbleSerial(rs: Boolean, data: Int){}

    private fun writeNibble(rs: Boolean, data: Int){}

    private fun writeByte(rs: Boolean, data: Int){}

    private fun writeCMD(data: Int){}

    private fun writeDATA(data: Int){}

    fun init(){}

    fun write(c: Char){}

    fun write(text: String){}

    fun cursor(line: Int, column: Int){}

    fun clear(){}
}

