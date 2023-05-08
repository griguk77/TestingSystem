package domain.usecases

import androidx.lifecycle.LiveData
import domain.repository.TestRepository

class GetCountQueUseCase(private val testRepository: TestRepository) {
    fun getCountQue(testName: String): LiveData<Int> {
        return testRepository.getCountQue(testName)
    }
}