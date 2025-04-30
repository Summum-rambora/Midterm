package com.example.midterm.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_db").build()
    }

    single { get<AppDatabase>().trainingDao() }

    single<TrainingRepository> { TrainingRepositoryImpl(get()) }
}
