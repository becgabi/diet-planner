package com.ptma.di

import co.zsmb.rainbowcake.dagger.RainbowCakeComponent
import co.zsmb.rainbowcake.dagger.RainbowCakeModule
import com.ptma.data.disk.DiskModule
import com.ptma.data.network.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RainbowCakeModule::class,
        ApplicationModule::class,
        ViewModelModule::class,
        DiskModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : RainbowCakeComponent
