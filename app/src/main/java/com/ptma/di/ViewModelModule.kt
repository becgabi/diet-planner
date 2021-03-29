package com.ptma.di

import androidx.lifecycle.ViewModel
import co.zsmb.rainbowcake.dagger.ViewModelKey
import com.ptma.ui.appointmentlist.AppointmentListViewModel
import com.ptma.ui.login.LoginViewModel
import com.ptma.ui.workoutdetail.WorkoutDetailViewModel
import com.ptma.ui.workoutlist.WorkoutListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AppointmentListViewModel::class)
    abstract fun bindAppointmentListViewModel(appointmentListViewModel: AppointmentListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WorkoutListViewModel::class)
    abstract fun bindWorkoutListViewModel(workoutListViewModel: WorkoutListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WorkoutDetailViewModel::class)
    abstract fun bindWorkoutDetailViewModel(workoutDetailViewModel: WorkoutDetailViewModel): ViewModel

}
