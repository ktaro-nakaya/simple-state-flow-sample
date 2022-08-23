package jp.co.casareal.simplestateflowsample

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MyViewModel:ViewModel() {
    val myStateFlowCounter = MutableStateFlow(0)
    val myStateFlowText = MutableStateFlow("")
}