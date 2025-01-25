package vip.cdms.maimaihelper

import com.google.zxing.*
import com.google.zxing.client.j2se.BufferedImageLuminanceSource
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.awt.image.BufferedImage

object QrRefactor {
    private const val SIZE = 300

    fun decode(qr: BufferedImage): String {
        val hints = mapOf(
            DecodeHintType.CHARACTER_SET to "UTF-8",
            DecodeHintType.TRY_HARDER to true,
        )
        val source = BufferedImageLuminanceSource(qr)
        @Suppress("SpellCheckingInspection")
        val binarizer = HybridBinarizer(source)
        val bitmap = BinaryBitmap(binarizer)
        val result = MultiFormatReader().decode(bitmap, hints)
        return result.text
    }

    fun encode(content: String): BufferedImage {
        val hints = mapOf(
            EncodeHintType.ERROR_CORRECTION to ErrorCorrectionLevel.M,
            EncodeHintType.QR_VERSION to 4,
            EncodeHintType.CHARACTER_SET to "UTF-8",
            EncodeHintType.MARGIN to 1,
        )
        val writer = MultiFormatWriter()
        val matrix = writer.encode(content, BarcodeFormat.QR_CODE, SIZE, SIZE, hints)
        return MatrixToImageWriter.toBufferedImage(matrix)
    }
}
