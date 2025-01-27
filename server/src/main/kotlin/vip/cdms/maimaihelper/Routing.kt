package vip.cdms.maimaihelper

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

fun Application.helperRouting() {
    val config = HelperConfig(environment.config)
    val desktopSwitcher = DesktopSwitcher(config.desktopSwitcher.to, config.desktopSwitcher.back)

    routing {
        @Suppress("SpellCheckingInspection")
        get("/maimaihelper") {
            if (config.token != null && call.queryParameters["token"] != config.token)
                return@get call.respondText("Invalid token", status = HttpStatusCode.Unauthorized)

            desktopSwitcher.switchTo()

            val output = QrCapture.shot()

            desktopSwitcher.switchBack()

            val outputStream = ByteArrayOutputStream()
            ImageIO.write(output, "png", outputStream)
            val outputBytes = outputStream.toByteArray()
            call.respondBytes(outputBytes, ContentType.Image.PNG)
        }
    }
}
