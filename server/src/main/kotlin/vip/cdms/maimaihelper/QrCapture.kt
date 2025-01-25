package vip.cdms.maimaihelper

import java.awt.Color
import java.awt.image.BufferedImage

object QrCapture {
    private val frameButton = ActionJFrame("1", Color.RED)
    private val frameCard = ActionJFrame("2", Color.GREEN)
    private val frameQr = ActionJFrame("3", Color.BLUE, 250, 250)
    private val frameClose = ActionJFrame("4", Color.DARK_GRAY)

    fun init() {
        frameButton.isVisible = true
        frameCard.isVisible = true
        frameQr.isVisible = true
        frameClose.isVisible = true
    }

    fun shot(): BufferedImage = with(ScreenRobot) {
        frameButton.click()
        delay(3000)
        frameCard.click()
        delay(1500)
        val screenshot = frameQr.capture()
        frameClose.click()
        screenshot
    }
}
