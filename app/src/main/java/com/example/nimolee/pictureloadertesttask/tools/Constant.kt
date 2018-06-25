package com.example.nimolee.pictureloadertesttask.tools

import android.arch.lifecycle.MutableLiveData
import com.example.nimolee.pictureloadertesttask.data.`object`.PictureObject

class Constant {
    companion object {
        val allImages = MutableLiveData<ArrayList<PictureObject>>()
        var loading = false
    }
}