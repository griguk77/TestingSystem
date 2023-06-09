package domain.usecases

import domain.repository.TestRepository

class FinishTestUseCase(private val testRepository: TestRepository) {
    suspend fun finishTest(testName: String, point: Int, userName: String): String {
        return testRepository.finishTest(testName, point, userName)
    }
}