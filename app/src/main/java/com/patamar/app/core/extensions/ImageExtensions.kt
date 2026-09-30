package com.patamar.app.core.extensions

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import coil.size.Size
import coil.transform.Transformation

// O mockup usa fotos em preto e branco: tira a saturação da imagem carregada pelo Coil.
class GrayscaleTransformation : Transformation {
    override val cacheKey: String = "patamar_grayscale"

    override suspend fun transform(input: Bitmap, size: Size): Bitmap {
        val output = Bitmap.createBitmap(input.width, input.height, Bitmap.Config.ARGB_8888)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f) })
        }
        Canvas(output).drawBitmap(input, 0f, 0f, paint)
        return output
    }
}
