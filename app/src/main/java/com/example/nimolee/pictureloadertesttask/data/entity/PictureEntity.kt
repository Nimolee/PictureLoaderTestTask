package com.example.nimolee.pictureloadertesttask.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Pictures")
data class PictureEntity(@PrimaryKey(autoGenerate = true) val id: Int,
                         @ColumnInfo(name = "picture_url") val url: String,
                         @ColumnInfo(name = "picture_compressed", typeAffinity = ColumnInfo.BLOB) val picture: ByteArray?)
