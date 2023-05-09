package ru.studyguk.testingsystem.presentation.viewmodel

import androidx.lifecycle.ViewModel
import domain.usecases.*

class MainViewModel(
    private val chooseTestUseCase: ChooseTestUseCase,
    private val continueTestUseCase: ContinueTestUseCase,
    private val finishTestUseCase: FinishTestUseCase,
    private val getCountQueUseCase: GetCountQueUseCase,
    private val loginUseCase: LoginUseCase,
    private val openCatalogUseCase: OpenCatalogUseCase,
    private val registrUseCase: RegistrUseCase,
    private val showAllResultsUseCase: ShowAllResultsUseCase,
    private val startTestUseCase: StartTestUseCase
): ViewModel() {

}