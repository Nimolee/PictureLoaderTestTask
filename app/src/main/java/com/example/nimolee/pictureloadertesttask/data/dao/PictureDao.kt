package com.example.nimolee.pictureloadertesttask.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.nimolee.pictureloadertesttask.data.entity.PictureEntity

@Dao
interface PictureDao {
    @Query("select * from Pictures order by id desc")
    fun getAllPictures(): Array<PictureEntity>?

    @Insert
    fun insertPicture(pictureEntity: PictureEntity)

    @Delete
    fun removepicture(pictureEntity: PictureEntity)
}