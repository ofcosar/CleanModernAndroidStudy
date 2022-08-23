package com.example.cleanmodernandroidstudy.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanmodernandroidstudy.callbacks.UpdateImageListener
import com.example.cleanmodernandroidstudy.model.ImageModel
import com.example.cleanmodernandroidstudy.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class FoodSelectViewModel : ViewModel() {
    private var _imageUrl = MutableLiveData("")
    private val repository = Repository()
    private var imageListener : UpdateImageListener? = null
    val imageUrl get() = _imageUrl
    private val foods = arrayOf("biryani","burger", "butter", "dessert" ,"dosa" ,"idly", "pasta", "pizza", "rice", "samosa")
    var foodPos = MutableLiveData(0)
    private var _imageLoaded = MutableLiveData(false)
    val imageLoaded get() = _imageLoaded
    init {
        loadImageUrl()
    }
    fun setUpdateListener(listener: UpdateImageListener){
        this.imageListener = listener
    }

    fun loadImageUrl(){
        _imageLoaded.value = false
        var response : Response<ImageModel>
        viewModelScope.launch {
        withContext(Dispatchers.IO)    {
            response = repository.imageService.getImage(foods[foodPos.value!!])

        }
            _imageUrl.value = response.body()?.image
            imageUrl.value?.let { imageListener?.updateImage(it,_imageLoaded) }
        }
    }
}