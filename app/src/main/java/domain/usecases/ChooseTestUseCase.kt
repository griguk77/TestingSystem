package domain.usecases

import domain.repository.TestRepository

class ChooseTestUseCase (private val testRepository: TestRepository) {
    fun chooseTest(testName: String): String {
        return testRepository.chooseTest(testName)
    }
}