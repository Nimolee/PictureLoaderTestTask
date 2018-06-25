package com.example.nimolee.pictureloadertesttask.ui

import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.nimolee.pictureloadertesttask.data.Repository

class MainViewModel : ViewModel() {
    private lateinit var repository: Repository

    fun init(context: Context) {
        repository = Repository(context)
    }

    fun openUrl(url: String) {
        repository.insert(url)
    }

    fun clearDatabase(){
        repository.clearDatabase()
    }
}