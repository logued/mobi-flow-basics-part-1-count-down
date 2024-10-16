package com.example.flowbasicspart1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class MainViewModel : ViewModel() {     // inherit from ViewModel (VM)

    val startingValue = 10;     // expose this value for initial composition

    // the source of data in this VM is a Flow.
    // the Flow emits an Int every second, and that Int value
    // is collected in the MainActivity for display in UI

    val countDownFlow = flow<Int> {     // flow builder, create flow in a coroutine
        //val startingValue = 10
        var currentValue = startingValue

        while (currentValue >= 0) {
            emit(currentValue);     // emits a value which invokes collectAsState() in MainActivity
            currentValue--
            delay(1000L)    // coroutine paused for 1 second
        }
    }

//   // As an alternative to viewing the output in a Compose UI, we can
//   // collect the emitted values and output to the Logcat.
//
//    init {
//        // init is an initialization block that is called on class construction.
//        collectFlow()
//    }
//
//    private fun collectFlow() {
//        viewModelScope.launch {
//            countDownFlow.collect { time ->
//                println("The current time is $time")
//            }
//        }
//    }

}