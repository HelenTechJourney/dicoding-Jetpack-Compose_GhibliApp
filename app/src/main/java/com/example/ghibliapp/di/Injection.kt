package com.example.ghibliapp.di

import com.example.ghibliapp.data.GhibliRepository

object Injection {
    fun provideRepository(): GhibliRepository {
        return GhibliRepository.getInstance()
    }
}