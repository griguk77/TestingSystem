package domain.usecases

import domain.models.Question
import domain.models.Test
import domain.repository.TestRepository

class StartTestUseCase (private val testRepository: TestRepository) {
    fun startTest(test: Test): Question {
        return testRepository.startTest(test)
    }
}