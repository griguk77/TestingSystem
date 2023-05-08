package domain.usecases

import androidx.lifecycle.LiveData
import domain.models.Question
import domain.repository.TestRepository

class StartTestUseCase (private val testRepository: TestRepository) {
    fun startTest(testName: String): LiveData<Question> {
        return testRepository.startTest(testName)
    }
}