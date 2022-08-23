package com.example.cleanmodernandroidstudy.repository

import com.example.cleanmodernandroidstudy.service.RetrofitInstance

class Repository {
    val imageService = RetrofitInstance.getImageService()
}