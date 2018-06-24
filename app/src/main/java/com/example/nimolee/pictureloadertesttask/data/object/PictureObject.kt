package com.example.nimolee.pictureloadertesttask.data.`object`

import android.graphics.Bitmap
import android.graphics.BitmapFactory

class PictureObject(val id: Int, val url: String, val status: Int, blob: ByteArray?) {
    var picture: Bitmap? = null

    init {
        if(blob!=null) {
            picture = BitmapFactory.decodeByteArray(blob, 0, blob?.size!!)
        }
    }
}