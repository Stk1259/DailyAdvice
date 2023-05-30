package com.example.dailyadvice.data

import android.util.Log

object AdviceDataSource {
    var adviceList = mutableListOf<AdviceModel>()
    private var adviceIndex = 0
    fun shuffleList(): AdviceModel {
        adviceList = mutableListOf(
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
        adviceList.shuffle()
        Log.d("list", adviceList.toString())
        adviceIndex = 0
        return adviceList[adviceIndex]
    }

    fun getNextAdvice(): AdviceModel {
        adviceIndex = (adviceIndex + 1) % adviceList.size
        return if (adviceIndex == adviceList.size - 1) {
            shuffleList()
            adviceList[adviceIndex]
        } else{
            adviceList[adviceIndex]
        }
    }
}
