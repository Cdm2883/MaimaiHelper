package vip.cdms.maimaihelper

import java.awt.Color
import java.awt.Font
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionAdapter
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.SwingConstants

class ActionJFrame(
    private val text: String,
    private val background: Color,
    width: Int = 80,
    height: Int = 80,
) : JFrame() {
    init {
        setBounds(
            80 + (0..500).random(),
            80 + (0..500).random(),
            width,
            height
        )
        isUndecorated = true
        isAlwaysOnTop = true
        initContent()
        initDraggable()
    }

    private fun initContent() {
        val label = JLabel(text)
        label.setHorizontalAlignment(SwingConstants.CENTER)
        label.setVerticalAlignment(SwingConstants.CENTER)
        label.foreground = background.getComplementary()
        label.font = Font("Arial", Font.BOLD, 20)
        add(label)
        contentPane.background = background
    }
    private fun Color.getComplementary() =
        Color(255 - red, 255 - green, 255 - blue)

    private var isDragging = false
    private var mouseX = 0
    private var mouseY = 0
    private fun initDraggable() {
        addMouseListener(object : MouseAdapter() {
            override fun mousePressed(e: MouseEvent) {
                isDragging = true
                mouseX = e.x
                mouseY = e.y
            }
            override fun mouseReleased(e: MouseEvent) {
                isDragging = false
            }
        })
        addMouseMotionListener(object : MouseMotionAdapter() {
            override fun mouseDragged(e: MouseEvent) {
                if (!isDragging) return
                val left = location.x
                val top = location.y
                setLocation(left + e.x - mouseX, top + e.y - mouseY)
            }
        })
    }
}
