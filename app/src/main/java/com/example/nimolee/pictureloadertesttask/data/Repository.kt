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

    fun getAllPictures(): MutableLiveData<ArrayList<PictureObject>> {
        val result = MutableLiveData<ArrayList<PictureObject>>()
        val task = Runnable {
            val pictures = dataBase?.pictureDao()?.getAllPictures()
            if (pictures != null) {
                val decPictures = ArrayList<PictureObject>()
                for (i in pictures) {
                    decPictures.add(PictureObject(i.id, i.url, i.picture))
                }
                result.postValue(decPictures)
            }
        }
        workerThread.postTask(task)
        return result
    }
}