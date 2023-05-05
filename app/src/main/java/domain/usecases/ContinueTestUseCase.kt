package domain.usecases

import domain.models.Question
import domain.models.Test
import domain.repository.TestRepository

class ContinueTestUseCase (private val testRepository: TestRepository) {
    fun continueTest(test: Test): Question {
        return testRepository.continueTest(test)
    }
}