package domain.usecases

import domain.models.Question
import domain.repository.TestRepository

class StartTestUseCase (private val testRepository: TestRepository) {
    fun startTest(testName: String, queNum: Int): Question {
        return testRepository.startTest(testName, queNum)
    }
}