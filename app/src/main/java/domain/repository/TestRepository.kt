package domain.repository

import androidx.lifecycle.LiveData
import domain.models.*

interface TestRepository {

    suspend fun chooseTest(testName: String): String

    fun continueTest(testName: String, queNum: Int): LiveData<Question>

    fun finishTest(testName: String, point: Double, userName: String): LiveData<String>

    fun login(user: User): Boolean

    fun registr(user: User): Boolean

    fun showAllResults(testName: String): LiveData<List<Result>>

    fun startTest(testName: String): LiveData<Question>

    suspend fun openCatalog(): List<String>

    fun getCountQue(testName: String): LiveData<Int>
}