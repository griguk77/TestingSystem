package domain.usecases

import domain.models.Test
import domain.repository.TestRepository

class ChooseTestUseCase (private val testRepository: TestRepository) {
    fun chooseTest(testName: String): Test {
        return testRepository.chooseTest(testName)
    }
}