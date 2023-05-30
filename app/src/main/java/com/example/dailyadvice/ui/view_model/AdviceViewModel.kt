package com.example.dailyadvice.ui.view_model


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dailyadvice.R
import com.example.dailyadvice.data.AdviceDataSource
import com.example.dailyadvice.data.AdviceModel
import com.example.dailyadvice.data.BackgroundDataSource
import com.example.dailyadvice.data.BackgroundModel

class AdviceViewModel : ViewModel() {

    lateinit var lifecycleOwner: LifecycleOwner

    private val currentAdvice = MutableLiveData<AdviceModel>()
    val currentAdviceLiveData: LiveData<AdviceModel> = currentAdvice

    private val currentBackground = MutableLiveData<BackgroundModel>()
    val currentBackgroundLiveData: LiveData<BackgroundModel> = currentBackground

    fun getNext(){
        currentAdvice.postValue(AdviceDataSource.getNextAdvice())
        currentBackground.postValue(BackgroundDataSource.getNextBackground())
    }
}

