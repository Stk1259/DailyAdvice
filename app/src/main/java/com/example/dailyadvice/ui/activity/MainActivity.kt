package com.example.dailyadvice.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleOwner
import com.example.dailyadvice.ui.view.AdviceScreen
import com.example.dailyadvice.ui.view_model.AdviceViewModel

class MainActivity: ComponentActivity(), LifecycleOwner {
    private val textViewModel = AdviceViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AdviceScreen(textViewModel, this)
        }
    }

    @Composable
    fun AdviceApp(viewModel: AdviceViewModel) {
        AdviceScreen(viewModel, this)
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        AdviceApp(textViewModel)
    }
}