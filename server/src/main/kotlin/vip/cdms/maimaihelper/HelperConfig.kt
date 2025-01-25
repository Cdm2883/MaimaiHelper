package vip.cdms.maimaihelper

import io.ktor.server.config.*

class HelperConfig(private val config: ApplicationConfig) {
    private fun property(key: String) = config.propertyOrNull("helper.$key")

    val token = property("token")?.getString()

    inner class DesktopSwitcher {
        val to = property("desktop-switcher.to")?.getString()?.toInt() ?: 0
        val back = property("desktop-switcher.back")?.getString()?.toInt() ?: -to
    }
    val desktopSwitcher = DesktopSwitcher()
}
