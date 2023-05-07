package domain.repository

import domain.models.*

interface TestRepository {

    fun chooseTest(testName: String): String

    fun continueTest(testName: String, queNum: Int): Question

    fun finishTest(testName: String, point: Int, userName: String): String

    fun login(user: User)

    fun registr(user: User)

    fun showAllResults(testName: String): List<Result>

    fun startTest(testName: String, queNum: Int): Question

    fun openCatalog(): List<String>
}