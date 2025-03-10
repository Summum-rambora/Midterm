package com.example.midterm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {
    var showSecondScreen by remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = !showSecondScreen,
        enter = fadeIn(animationSpec = tween(500)),
        exit = fadeOut(animationSpec = tween(500))
    ) {
        FirstScreen { showSecondScreen = true }
    }

    AnimatedVisibility(
        visible = showSecondScreen,
        enter = fadeIn(animationSpec = tween(500)),
        exit = fadeOut(animationSpec = tween(500))
    ) {
        SecondScreen { showSecondScreen = false }
    }
}

@Composable
fun FirstScreen(onNavigate: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("My hobby is powerlifting", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigate, shape = RoundedCornerShape(8.dp)) {
            Text("More...")
        }
    }
}

@Composable
fun SecondScreen(onBack: () -> Unit) {
    val trainingPlan = listOf(
        "Monday: Squats & Deadlifts",
        "Tuesday: Bench Press & Shoulders",
        "Wednesday: Rest or Cardio",
        "Thursday: Deadlifts & Back",
        "Friday: Bench Press & Arms",
        "Saturday: Squats & Core",
        "Sunday: Rest Day"
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("I love lifting high weight!!!", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(trainingPlan) { item ->
                Text(item, fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack, shape = RoundedCornerShape(8.dp)) {
            Text("Back")
        }
    }
}
