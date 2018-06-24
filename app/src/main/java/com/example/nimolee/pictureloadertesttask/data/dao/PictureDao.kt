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

    @Query("delete from Pictures where id = :id")
    fun removePicture(id: Int)

    @Query("update Pictures set status = :newStatus where id = :id")
    fun changeStatus(id: Int, newStatus: Int)

    @Query("update Pictures set picture = :picture where id = :id")
    fun savePicture(id: Int,picture:ByteArray)


}