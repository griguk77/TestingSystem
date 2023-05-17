package domain.usecases

import androidx.lifecycle.LiveData
import domain.repository.TestRepository

class GetCountQueUseCase(private val testRepository: TestRepository) {
    suspend fun getCountQue(testName: String): Int {
        return testRepository.getCountQue(testName)
    }
}