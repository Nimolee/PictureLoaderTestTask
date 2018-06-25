package com.example.nimolee.pictureloadertesttask.ui.fragment.picture.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.example.nimolee.pictureloadertesttask.data.Repository
import com.example.nimolee.pictureloadertesttask.data.`object`.PictureObject
import com.example.nimolee.pictureloadertesttask.tools.Constant.Companion.loading
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream

class PictureListViewModel : ViewModel() {
    private lateinit var repository: Repository

    fun init(context: Context) {
        repository = Repository(context)
    }

    fun saveImageToDatabase(id: Int, url: String) {
        val target = object : com.squareup.picasso.Target {
            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                loading = false
                if (e?.message?.contains("Unrecognized")!!) {
                    repository.changeStatus(id, 3)
                } else {
                    repository.changeStatus(id, 4)
                }
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                val image: ByteArray?
                if (bitmap != null) {
                    val stream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    image = stream.toByteArray()
                    loading = false
                    if (image != null) {
                        repository.savePicture(id, image)
                    }
                }
            }
        }
        Picasso.get().load(url).resize(768,0).into(target)
    }

    fun getAllImage(): MutableLiveData<ArrayList<PictureObject>> {
        return repository.getAllPictures()
    }

    fun refresh() {
        repository.refreshAll()
    }
}