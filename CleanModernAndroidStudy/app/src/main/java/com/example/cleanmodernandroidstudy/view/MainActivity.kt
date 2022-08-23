package com.example.cleanmodernandroidstudy.view

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.cleanmodernandroidstudy.R
import com.example.cleanmodernandroidstudy.callbacks.UpdateImageListener
import com.example.cleanmodernandroidstudy.databinding.ActivityMainBinding
import com.example.cleanmodernandroidstudy.viewmodel.FoodSelectViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val viewModel : FoodSelectViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)

        viewModel.setUpdateListener(object : UpdateImageListener{
            override fun updateImage(url: String, loaded: MutableLiveData<Boolean>) {
                lifecycleScope.launch {
                    withContext(Dispatchers.Main) {
                       val myPic = Glide.with(this@MainActivity).load(url)
                        myPic.listener(object : RequestListener<Drawable>{
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                TODO("Not yet implemented")
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                loaded.value = true
                                return false
                            }

                        }).into(binding.foodImage)
                    }

                }


            }

        })
    }
}