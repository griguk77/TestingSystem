package domain.usecases

import androidx.lifecycle.LiveData
import domain.models.Question
import domain.repository.TestRepository

class ContinueTestUseCase (private val testRepository: TestRepository) {
    fun continueTest(testName: String, queNum: Int): LiveData<Question> {
        return testRepository.continueTest(testName, queNum)
    }
}