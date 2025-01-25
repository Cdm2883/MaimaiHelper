package vip.cdms.maimaihelper

import java.awt.Rectangle
import java.awt.Robot
import java.awt.event.MouseEvent
import java.awt.image.BufferedImage
import javax.swing.JFrame

object ScreenRobot {
    private val JFrame.robot
        get() = Robot(getGraphicsDevice())
    private fun JFrame.getGraphicsDevice() =
        graphicsConfiguration.device

    fun delay(ms: Int) = Robot().delay(ms)

    @Suppress("DEPRECATION")
    fun JFrame.click() = with(robot){
        isVisible = false
        mouseMove(location.x, location.y)
        delay(100)
        mousePress(MouseEvent.BUTTON1_MASK)
        delay(100)
        mouseRelease(MouseEvent.BUTTON1_MASK)
        delay(100)
        isVisible = true
    }

    fun JFrame.capture(): BufferedImage {
        isVisible = false
        val bound = Rectangle(location, size)
        val screenshot = robot.createScreenCapture(bound)
        isVisible = true
        return screenshot
    }
}
