package com.example.dailyadvice.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.lifecycle.LifecycleOwner
import com.example.dailyadvice.data.AdviceDataSource
import com.example.dailyadvice.data.BackgroundDataSource
import com.example.dailyadvice.ui.view.AdviceScreen
import com.example.dailyadvice.ui.view_model.AdviceViewModel

class MainActivity: ComponentActivity(), LifecycleOwner {
    private val adviceViewModel = AdviceViewModel()
    init {
        AdviceDataSource.shuffleList()
        BackgroundDataSource.shuffleBackground()
        val adviceViewModel = AdviceViewModel()
        adviceViewModel.getNext()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContent {
            AdviceScreen(adviceViewModel, this)
        }
    }

    @Composable
    fun AdviceApp(viewModel: AdviceViewModel) {
        AdviceScreen(viewModel, this)
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        AdviceApp(adviceViewModel)
    }
}