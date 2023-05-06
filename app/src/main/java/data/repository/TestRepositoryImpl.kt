package data.repository

import domain.models.Question
import domain.models.Result
import domain.models.Test
import domain.models.TextResult
import domain.models.User
import domain.repository.TestRepository

object TestRepositoryImpl: TestRepository {
    override fun chooseTest(testName: String): Test {
        TODO("Not yet implemented")
    }

    override fun continueTest(test: Test): Question {
        TODO("Not yet implemented")
    }

    override fun finishTest(test: Test): TextResult {
        TODO("Not yet implemented")
    }

    override fun login(user: User) {
        TODO("Not yet implemented")
    }

    override fun registr(user: User) {
        TODO("Not yet implemented")
    }

    override fun showAllResults(test: Test): List<Result> {
        TODO("Not yet implemented")
    }

    override fun startTest(test: Test): Question {
        TODO("Not yet implemented")
    }

    override fun openCatalog(): List<Test> {
        TODO("Not yet implemented")
    }

}