package com.example.nimolee.pictureloadertesttask.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.nimolee.pictureloadertesttask.data.dao.PictureDao
import com.example.nimolee.pictureloadertesttask.data.entity.PictureEntity

@Database(entities = [PictureEntity::class], version = 1)
abstract class PicturesDataBase : RoomDatabase() {
    abstract fun pictureDao(): PictureDao

    companion object {
        private var INSTANCE: PicturesDataBase? = null

        fun getInstance(context: Context): PicturesDataBase? {
            if (INSTANCE == null) {
                synchronized(PicturesDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            PicturesDataBase::class.java,
                            "video.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}