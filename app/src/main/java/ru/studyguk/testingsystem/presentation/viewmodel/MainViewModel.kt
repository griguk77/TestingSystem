package ru.studyguk.testingsystem.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.models.Question
import domain.models.User
import domain.usecases.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val chooseTestUseCase: ChooseTestUseCase,
    private val continueTestUseCase: ContinueTestUseCase,
    private val finishTestUseCase: FinishTestUseCase,
    private val getCountQueUseCase: GetCountQueUseCase,
    private val loginUseCase: LoginUseCase,
    private val openCatalogUseCase: OpenCatalogUseCase,
    private val registrUseCase: RegistrUseCase,
    private val showAllResultsUseCase: ShowAllResultsUseCase
) : ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private val _success = MutableStateFlow(false)
    // The UI collects from this StateFlow to get its state updates
    val success: StateFlow<Boolean> = _success

    private val _catalog = MutableLiveData<List<String>>()
    var catalog: LiveData<List<String>> = _catalog

    private val _testName = MutableLiveData<String>()
    var testName: LiveData<String> = _testName

    private val _declaration = MutableLiveData<String>()
    var declaration: LiveData<String> = _declaration

    private val _question = MutableLiveData<Question>()
    var question: LiveData<Question> = _question

    private val _queCount = MutableLiveData<Int>()
    var queCount: LiveData<Int> = _queCount

    private val _pointsResult = MutableLiveData<Double>()
    var pointsResult: LiveData<Double> = _pointsResult

    private val _textResult = MutableLiveData<String>()
    var textResult: LiveData<String> = _textResult

    private val _results = MutableLiveData<List<domain.models.Result>>()
    var results: LiveData<List<domain.models.Result>> = _results

    fun login(user: User) {
        viewModelScope.launch {
            loginUseCase.login(user).collect{
                Log.d("RR", "$it")
                _success.value = it
                if (_success.value == true) {
                    _userName.value = user.name.substringBefore('@')
                }
            }
        }
    }

    fun registr(user: User) {
        _success.value = registrUseCase.registr(user)
        if (_success.value == true) {
            _userName.value = user.name.substringBefore('@')
        }
    }

    fun showCatalog() {
        viewModelScope.launch {
            _catalog.value = openCatalogUseCase.openCatalog()
        }
    }

    fun saveTestName(name: String) {
        _testName.value = name
    }

    fun getDeclTest(name: String) {
        viewModelScope.launch {
            _declaration.value = chooseTestUseCase.chooseTest(name)
        }
    }

    fun setQueNum(num: Int) {
        _question.value?.queNum = num
    }

    fun getQueCount(name: String) {
        viewModelScope.launch {
            _queCount.value = getCountQueUseCase.getCountQue(name)
        }
    }

    fun getQuestion(name: String, num: Int) {
        viewModelScope.launch {
            _question.value = continueTestUseCase.continueTest(name, num)
        }
    }

    fun setPoints(points: Double) {
        _pointsResult.value = points
    }

    fun getTextResult(nameTest: String, points: Int, nameUser: String) {
        viewModelScope.launch {
            _textResult.value = finishTestUseCase.finishTest(nameTest, points, nameUser)
        }
    }

    fun getAllResults(nameTest: String) {
        viewModelScope.launch {
            _results.value = showAllResultsUseCase.showAllResults(nameTest)
        }
    }
}