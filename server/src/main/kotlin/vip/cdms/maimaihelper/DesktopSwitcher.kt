package vip.cdms.maimaihelper

import java.awt.Robot
import java.awt.event.KeyEvent

class DesktopSwitcher(private val to: Int, private val back: Int) {
    fun switchTo() = switch(to)
    fun switchBack() = switch(back)

    // TODO(DesktopSwitcher): support another platform
    companion object {
        private val robot = Robot()

        fun switch(index: Int) {
            if (index == 0) return
            if (index > 0) repeat(index) {
                switchToNext()
                robot.delay(1000)
            } else repeat(-index) {
                switchToPrevious()
                robot.delay(1000)
            }
        }

        private fun switchToPrevious() {
            robot.keyPress(KeyEvent.VK_WINDOWS)
            robot.keyPress(KeyEvent.VK_CONTROL)
            robot.keyPress(KeyEvent.VK_LEFT)
            robot.keyRelease(KeyEvent.VK_LEFT)
            robot.keyRelease(KeyEvent.VK_CONTROL)
            robot.keyRelease(KeyEvent.VK_WINDOWS)
        }

        private fun switchToNext() {
            robot.keyPress(KeyEvent.VK_WINDOWS)
            robot.keyPress(KeyEvent.VK_CONTROL)
            robot.keyPress(KeyEvent.VK_RIGHT)
            robot.keyRelease(KeyEvent.VK_RIGHT)
            robot.keyRelease(KeyEvent.VK_CONTROL)
            robot.keyRelease(KeyEvent.VK_WINDOWS)
        }
    }
}
