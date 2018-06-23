package com.example.nimolee.pictureloadertesttask.data

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.example.nimolee.pictureloadertesttask.data.`object`.PictureObject
import com.example.nimolee.pictureloadertesttask.tools.DbWorkerThread

class Repository(context: Context) {
    private var dataBase = PicturesDataBase.getInstance(context)
    private var workerThread = DbWorkerThread("DBThread")

    init {
        workerThread.start()
    }

    fun getAllPictures():MutableLiveData<Array<PictureObject>>{

    }
}