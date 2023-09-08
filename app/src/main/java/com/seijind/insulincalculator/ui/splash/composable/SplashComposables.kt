package com.seijind.insulincalculator.ui.splash.composable

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.seijind.insulincalculator.R
import com.seijind.insulincalculator.ui.splash.navigation.GoHome
import com.seijind.insulincalculator.ui.theme.InsulinCalculatorTheme
import kotlinx.coroutines.delay

@Composable
internal fun SplashScreen(
    goHome: GoHome
) {
    SplashContent(goHome = { goHome() })
}

@Composable
private fun SplashContent(
    goHome: GoHome
) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(true) {
        scale.animateTo(targetValue = 1f, animationSpec = tween(durationMillis = 3000))
        delay(2000L)
        goHome()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo_insulin),
            contentDescription = "Love",
            contentScale = ContentScale.Fit,
            modifier = Modifier.scale(scale.value)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashContentPreview() {
    InsulinCalculatorTheme {
        SplashContent(goHome = {})
    }
}