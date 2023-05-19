package domain.usecases

import domain.models.Question
import domain.repository.TestRepository

class ContinueTestUseCase(private val testRepository: TestRepository) {
    suspend fun continueTest(testName: String, queNum: Int): Question {
        return testRepository.continueTest(testName, queNum)
    }
}