package domain.repository

import domain.models.*

interface TestRepository {

    fun chooseTest(testName: String): Test

    fun continueTest(test: Test): Question

    fun finishTest(test: Test): TextResult

    fun login(user: User)

    fun registr(user: User)

    fun showAllResults(test: Test): List<Result>

    fun startTest(test: Test): Question
}