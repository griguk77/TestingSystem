package ru.studyguk.testingsystem.presentation.viewmodel

import android.app.Activity
import android.app.Application
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import data.repository.TestRepositoryImpl
import domain.usecases.*

class MainViewModelFactory(private val application: Application, private val activity: FragmentActivity): ViewModelProvider.Factory {

    private val testRepository by lazy(LazyThreadSafetyMode.NONE) {
        TestRepositoryImpl(application, activity)
    }

    private val chooseTestUseCase by lazy(LazyThreadSafetyMode.NONE) {
        ChooseTestUseCase(testRepository)
    }

    private val continueTestUseCase by lazy(LazyThreadSafetyMode.NONE) {
        ContinueTestUseCase(testRepository)
    }

    private val finishTestUseCase by lazy(LazyThreadSafetyMode.NONE) {
        FinishTestUseCase(testRepository)
    }

    private val getCountQueUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetCountQueUseCase(testRepository)
    }

    private val loginUseCase by lazy(LazyThreadSafetyMode.NONE) {
        LoginUseCase(testRepository)
    }

    private val openCatalogUseCase by lazy(LazyThreadSafetyMode.NONE) {
        OpenCatalogUseCase(testRepository)
    }

    private val registrUseCase by lazy(LazyThreadSafetyMode.NONE) {
        RegistrUseCase(testRepository)
    }

    private val showAllResultsUseCase by lazy(LazyThreadSafetyMode.NONE) {
        ShowAllResultsUseCase(testRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            chooseTestUseCase,
            continueTestUseCase,
            finishTestUseCase,
            getCountQueUseCase,
            loginUseCase,
            openCatalogUseCase,
            registrUseCase,
            showAllResultsUseCase
        ) as T
    }

}