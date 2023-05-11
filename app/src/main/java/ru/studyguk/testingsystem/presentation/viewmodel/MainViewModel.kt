package ru.studyguk.testingsystem.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import domain.models.User
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
) : ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName



    fun login(user: User): Boolean {
        //val success = loginUseCase.login(user)
        val success = true
        if (success) {
            _userName.value = user.name.substringBefore('@')
        }
        return success
    }

    fun registr(user: User): Boolean {
        //val success = registrUseCase.registr(user)
        val success = false
        if (success) {
            _userName.value = user.name.substringBefore('@')
        }
        return success
    }
}