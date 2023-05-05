package domain.usecases

import domain.models.Test
import domain.models.TextResult
import domain.repository.TestRepository

class FinishTestUseCase (private val testRepository: TestRepository) {
    fun finishTest(test: Test): TextResult {
        return testRepository.finishTest(test)
    }
}