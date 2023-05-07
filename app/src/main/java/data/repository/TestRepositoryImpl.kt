package data.repository

import domain.models.Question
import domain.models.Result
import domain.models.User
import domain.repository.TestRepository

object TestRepositoryImpl: TestRepository {
    override fun chooseTest(testName: String): String {
        TODO("Not yet implemented")
    }

    override fun continueTest(testName: String, queNum: Int): Question {
        TODO("Not yet implemented")
    }

    override fun finishTest(testName: String, point: Int, userName: String): String {
        TODO("Not yet implemented")
    }

    override fun login(user: User) {
        TODO("Not yet implemented")
    }

    override fun registr(user: User) {
        TODO("Not yet implemented")
    }

    override fun showAllResults(testName: String): List<Result> {
        TODO("Not yet implemented")
    }

    override fun startTest(testName: String, queNum: Int): Question {
        TODO("Not yet implemented")
    }

    override fun openCatalog(): List<String> {
        TODO("Not yet implemented")
    }

}