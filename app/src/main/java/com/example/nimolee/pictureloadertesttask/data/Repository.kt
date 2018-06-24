package com.example.nimolee.pictureloadertesttask.data

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.example.nimolee.pictureloadertesttask.data.`object`.PictureObject
import com.example.nimolee.pictureloadertesttask.data.entity.PictureEntity
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
                    decPictures.add(PictureObject(i.id, i.url, i.status, i.picture))
                }
                result.postValue(decPictures)
            }
        }
        workerThread.postTask(task)
        return result
    }

    fun insert(url: String) {
        workerThread.postTask(Runnable { dataBase?.pictureDao()?.insertPicture(PictureEntity(0, url, 0, null)) })
    }

    fun remove(id: Int) {
        workerThread.postTask(Runnable { dataBase?.pictureDao()?.removePicture(id) })
    }

    fun changeStatus(id: Int, newStatus: Int) {
        workerThread.postTask(Runnable { dataBase?.pictureDao()?.changeStatus(id, newStatus) })
    }

    fun savePicture(id: Int, picture: ByteArray) {
        workerThread.postTask(Runnable {
            dataBase?.pictureDao()?.savePicture(id, picture)
            dataBase?.pictureDao()?.changeStatus(id, 2)
        })
    }
}