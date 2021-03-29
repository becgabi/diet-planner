package com.ptma.ui.workoutlist

import co.zsmb.rainbowcake.withIOContext
import javax.inject.Inject

class WorkoutListPresenter @Inject constructor() {

    suspend fun getData(): String = withIOContext {
        ""
    }

}
