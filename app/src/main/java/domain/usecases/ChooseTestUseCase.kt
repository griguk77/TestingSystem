package domain.usecases

import domain.repository.TestRepository

class ChooseTestUseCase(private val testRepository: TestRepository) {
    suspend fun chooseTest(testName: String): String {
        return testRepository.chooseTest(testName)
    }
}