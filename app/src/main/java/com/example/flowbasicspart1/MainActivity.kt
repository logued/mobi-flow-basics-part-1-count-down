package com.example.flowbasicspart1     // Flow Basics 1 - Oct 2024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flowbasicspart1.ui.theme.FlowBasicsPart1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlowBasicsPart1Theme {
                // create new or return existing ViewModel (VM) for this Activity
                // The VM will store the data that is source of State for the UI.
                val viewModel = viewModel<MainViewModel>()

                // Collect each emitted Int from the Flow and store as a State<Int> type.
                // State types are Observable, so compose will observe 'time' for updates.
                // Every change in the 'time' State value will cause a recompose - to update the UI.
                // For the initial composition, we initialize the state to the startingValue stored in the VM.
                //
                val time = viewModel.countDownFlow.collectAsState(initial = viewModel.startingValue)

                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        // this composable depends on 'time', which is a State object storing Int values,
                        // so, every update of 'time' causes a recompose of this composable function.
                        // We extract the value from the 'time' State object using .value
                        text = time.value.toString(),
                        fontSize = 30.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}