package com.ptma.ui.workoutdetail

import co.zsmb.rainbowcake.withIOContext
import javax.inject.Inject

class WorkoutDetailPresenter @Inject constructor() {

    suspend fun getData(): String = withIOContext {
        ""
    }

}
