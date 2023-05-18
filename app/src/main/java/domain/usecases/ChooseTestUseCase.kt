package domain.usecases

import androidx.lifecycle.LiveData
import domain.repository.TestRepository

class ChooseTestUseCase (private val testRepository: TestRepository) {
    suspend fun chooseTest(testName: String): String {
        return testRepository.chooseTest(testName)
    }
}