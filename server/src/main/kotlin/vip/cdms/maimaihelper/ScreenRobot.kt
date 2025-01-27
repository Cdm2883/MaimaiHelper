package vip.cdms.maimaihelper

import com.google.zxing.ReaderException
import com.google.zxing.BinaryBitmap
import com.google.zxing.qrcode.QRCodeReader
import com.google.zxing.client.j2se.BufferedImageLuminanceSource
import com.google.zxing.common.HybridBinarizer
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
    fun JFrame.click() = with(robot) {
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

    fun BufferedImage.hasQRCode(): Boolean {
        return try {
            QRCodeReader().decode(
                BinaryBitmap(
                    HybridBinarizer(
                        BufferedImageLuminanceSource(this)
                    )
                )
            )
            true
        } catch (e: ReaderException) {
            false
        } catch (e: Throwable) {
            false
        }
    }

    fun waitForQR(
        capture: () -> BufferedImage,
        timeout: Long = 15000,
        interval: Long = 300
    ): BufferedImage {
        val start = System.currentTimeMillis()
        while (System.currentTimeMillis() - start < timeout) {
            val img = capture()
            if (img.hasQRCode()) return img
            delay(interval.toInt())
        }
        throw RuntimeException("QR code not detected in ${timeout}ms")
    }
}