package jp.co.casareal.simplestateflowsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import jp.co.casareal.simplestateflowsample.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        val viewModel by viewModels<MyViewModel>()

        binding.model = viewModel

        binding.btnCountUp.setOnClickListener {
            viewModel.myStateFlowCounter.value = viewModel.myStateFlowCounter.value + 1

//            lifecycleScope.launch {
//                viewModel.myStateFlowCounter.emit(viewModel.myStateFlowCounter.value + 1)
//            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.myStateFlowText.collect {
                    withContext(Dispatchers.Main) {
                        binding.textStringCount.text = "${it.length}文字です。"
                    }
                }
            }
        }
    }
}