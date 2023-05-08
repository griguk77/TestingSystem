package domain.repository

import androidx.lifecycle.LiveData
import domain.models.*

interface TestRepository {

    fun chooseTest(testName: String): LiveData<String>

    fun continueTest(testName: String, queNum: Int): LiveData<Question>

    fun finishTest(testName: String, point: Int, userName: String): LiveData<String>

    fun login(user: User)

    fun registr(user: User)

    fun showAllResults(testName: String): LiveData<List<Result>>

    fun startTest(testName: String): LiveData<Question>

    fun openCatalog(): LiveData<List<String>>

    fun getCountQue(testName: String): LiveData<Int>
}