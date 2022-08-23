package com.example.cleanmodernandroidstudy.callbacks

import androidx.lifecycle.MutableLiveData

interface UpdateImageListener {
    fun updateImage(url : String, loaded : MutableLiveData<Boolean>)
}