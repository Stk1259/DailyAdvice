package com.example.dailyadvice.ui.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.example.dailyadvice.R
import com.example.dailyadvice.ui.theme.HelveticaCompressed
import com.example.dailyadvice.ui.view_model.AdviceViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AdviceScreen(viewModel: AdviceViewModel, lifecycleOwner: LifecycleOwner) {
    val adviceList by viewModel.adviceList.observeAsState()
    val currentAdvice = remember { mutableStateOf(viewModel.currentAdvice.value) }
    val currentBackground = remember { mutableStateOf(viewModel.currentBackground.value) }
    val animateAdvice = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        AnimatedContent(
            targetState = currentBackground.value,
            transitionSpec = {
                EnterTransition.None with ExitTransition.None
            }) { currentBackground ->
            Image(
                painter = painterResource(
                    currentBackground ?: R.drawable.background1
                ),
                contentDescription = null,
                modifier = Modifier
                    .animateEnterExit(
                        enter = scaleIn(
                            animationSpec = tween(durationMillis = 400, delayMillis = 400),
                            initialScale = 0.8f
                        ) + fadeIn(animationSpec = tween(durationMillis = 400, delayMillis = 400)),
                        exit = scaleOut(
                            animationSpec = tween(durationMillis = 400),
                            targetScale = 0.8f
                        ) + fadeOut(animationSpec = tween(durationMillis = 400))
                    )
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .aspectRatio(1f)
                .align(Alignment.Center)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    animateAdvice.value = true
                    currentAdvice.value?.let { viewModel.getNextAdvice(it) }
                }
                .animateContentSize()
        ) {
            AnimatedContent(
                targetState = currentAdvice.value,
                transitionSpec = {
                    slideInHorizontally(
                        initialOffsetX = { width -> width },
                        animationSpec = tween(durationMillis = 800)
                    ) with slideOutHorizontally(
                        targetOffsetX = { width -> -width },
                        animationSpec = tween(durationMillis = 800)
                    )
                }
            ) { targetAdvice ->
                Text(
                    text = targetAdvice?.text?.uppercase() ?: "",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.W900,
                        fontFamily = HelveticaCompressed,
                        letterSpacing = 0.sp,
                        lineHeight = 60.sp
                    ),
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
        }
    }

    viewModel.currentAdvice.observe(lifecycleOwner) { newAdvice ->
        currentAdvice.value = newAdvice
    }

    viewModel.currentBackground.observe(lifecycleOwner) { newBackground ->
        currentBackground.value = newBackground
    }
}


