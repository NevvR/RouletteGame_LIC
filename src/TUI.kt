import isel.leic.utils.Time

object TUI {
    fun init(){
        KBD.init()
        LCD.init()
    }

    fun write(){
        var wrote = 0
        while (true) {
            while (wrote < 16) {
                LCD.write(KBD.waitKey(50000))
                wrote++
            }
            LCD.cursor(1, 0)
            wrote = 0
            while (wrote < 16) {
                LCD.write(KBD.waitKey(50000))
                wrote++
            }
            wrote = 0
            LCD.clear()
        }
    }
}

fun main() {
    HAL.init()
    KBD.init()
    LCD.init()
    TUI.write()
}