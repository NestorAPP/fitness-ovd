package com.mvd.applicant.ui.training

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TrainingScreen(vm: TrainingViewModel = viewModel()) {
    val state by vm.state.collectAsState()

    if (state.program == null) {
        TrainingInputScreen(
            vm = vm,
            onProgramReady = { /* state уже обновился */ }
        )
    } else {
        TrainingProgramScreen(
            vm = vm,
            onEditInput = { vm.resetProgram() }
        )
    }
}
