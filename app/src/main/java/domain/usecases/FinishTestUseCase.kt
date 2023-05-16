package domain.usecases

import androidx.lifecycle.LiveData
import domain.repository.TestRepository

class FinishTestUseCase (private val testRepository: TestRepository) {
    fun finishTest(testName: String, point: Double, userName: String): LiveData<String> {
        return testRepository.finishTest(testName, point, userName)
    }
}