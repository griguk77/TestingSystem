package domain.repository

import androidx.lifecycle.LiveData
import domain.models.*

interface TestRepository {

    suspend fun chooseTest(testName: String): String

    suspend fun continueTest(testName: String, queNum: Int): Question

    suspend fun finishTest(testName: String, point: Int, userName: String): String

    fun login(user: User): Boolean

    fun registr(user: User): Boolean

    fun showAllResults(testName: String): LiveData<List<Result>>

    suspend fun openCatalog(): List<String>

    suspend fun getCountQue(testName: String): Int
}