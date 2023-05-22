package com.example.dailyadvice.ui.view_model


import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dailyadvice.R
import com.example.dailyadvice.data.AdviceModel

class AdviceViewModel : ViewModel() {
    lateinit var lifecycleOwner: LifecycleOwner
    val adviceList: MutableLiveData<List<AdviceModel>> = MutableLiveData()
    val currentAdvice: MutableLiveData<AdviceModel> = MutableLiveData()
    val currentBackground: MutableLiveData<Int> = MutableLiveData()
    var backgroundList = listOf(
        R.drawable.background1,
        R.drawable.background2,
        R.drawable.background3,
        R.drawable.background4
    )
    var backgroundIndex = 0
    var adviceIndex = 0

    init {
        adviceList.value = listOf(
            AdviceModel("Не лезь не в свое дело"),
            AdviceModel("Не кури"),
            AdviceModel("Не бойся отказывать"),
            AdviceModel("Выражайся яснее"),
            AdviceModel("Запиши - тогда не забудешь"),
            AdviceModel("Не заморачивайся на мелочах"),
            AdviceModel("Не зацикливайся на одном решении"),
            AdviceModel("Умей вовремя заткнуться "),
            AdviceModel("Действуй быстро")
        )
        currentAdvice.value = adviceList.value?.first()
        currentBackground.value = backgroundList.first()
    }

    fun getNextAdvice(advice: AdviceModel) {
        adviceIndex = (adviceIndex + 1) % (adviceList.value?.size ?: 0)
        currentAdvice.value = adviceList.value?.get(adviceIndex)
        if (adviceIndex == adviceList.value!!.size - 1) {
            adviceList.value = adviceList.value?.shuffled()
            //TODO change random
        }
        backgroundIndex = (backgroundIndex + 1) % backgroundList.size
        currentBackground.value = backgroundList[backgroundIndex]
        if (backgroundIndex == backgroundList.size - 1) {
            backgroundList = backgroundList.shuffled()
            //TODO change random
        }
    }
}

