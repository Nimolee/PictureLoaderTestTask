package com.example.nimolee.pictureloadertesttask.data.`object`

import android.graphics.Bitmap
import android.graphics.BitmapFactory

class PictureObject(val id: Int, val url: String, blob: ByteArray?) {
    var picture: Bitmap? = null

    init {
        picture = BitmapFactory.decodeByteArray(blob, 0, blob?.size!!)
    }
}