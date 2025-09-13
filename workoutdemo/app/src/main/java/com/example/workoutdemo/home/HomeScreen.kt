package com.example.workoutdemo.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class) //used for topappbar
@Composable
fun HomeScreen(vm: HomeViewModel = viewModel()) {
    val time by vm.time.collectAsState()
    val steps by vm.steps.collectAsState()
    val calories by vm.calories.collectAsState()
    val running by vm.isRunning.collectAsState()

    //  LaunchedEffect: update stats every second when running
    LaunchedEffect(running) { // not invokes when running state is false
        Log.d("Workout","Ra1 launch effect")
        if (running) {
            while (true) {
                delay(1000)
                vm.increaseStats()
            }
        }
    }

    //  DisposableEffect: cleanup on stop
    DisposableEffect(running) {
        Log.d("Workout","Ra1 dispose effect")
        if (running) Log.d("Workout", "Workout started")
        onDispose {
            if (!running) Log.d("Workout", "Workout stopped, cleanup done")
        }
    }

    val context = LocalContext.current
    // SideEffect: show toast after every recomposition
    SideEffect {
        Toast.makeText(
            context,
            "Current steps: $steps",
            Toast.LENGTH_SHORT
        ).show()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Workout Tracker") }

            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Time: $time s")
            Text("Steps: $steps")
            Text("Calories: $calories")
            Spacer(Modifier.height(24.dp))


            Button(onClick = { vm.toggleWorkout() }) {
                Text(if (running) "Stop Workout" else "Start Workout")
            }
        }
    }
}
