package com.example.nimolee.pictureloadertesttask.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "Pictures")
data class PictureEntity(@PrimaryKey(autoGenerate = true) val id: Int,
                         @ColumnInfo(name = "picture_url") val url: String,
                         @ColumnInfo(name = "status") val status: Int,
                         @ColumnInfo(name = "picture_compressed", typeAffinity = ColumnInfo.BLOB) val picture: ByteArray?) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PictureEntity

        if (id != other.id) return false
        if (url != other.url) return false
        if (status != other.status) return false
        if (!Arrays.equals(picture, other.picture)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + url.hashCode()
        result = 31 * result + status
        result = 31 * result + (picture?.let { Arrays.hashCode(it) } ?: 0)
        return result
    }
}
