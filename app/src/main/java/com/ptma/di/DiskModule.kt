package com.ptma.di

import android.content.Context
import androidx.room.Room
import com.ptma.data.disk.AppDatabase
import com.ptma.data.disk.dao.AppointmentDao
import com.ptma.data.disk.dao.WorkoutDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DiskModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "ptma.db").build()
    }

    @Provides
    @Singleton
    fun provideAppointmentDao(appDB: AppDatabase): AppointmentDao = appDB.appointmentDao()

    @Provides
    @Singleton
    fun provideWorkoutDao(appDB: AppDatabase): WorkoutDao = appDB.workoutDao()
}