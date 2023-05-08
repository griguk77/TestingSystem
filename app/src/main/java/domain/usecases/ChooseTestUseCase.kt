package domain.usecases

import androidx.lifecycle.LiveData
import domain.repository.TestRepository

class ChooseTestUseCase (private val testRepository: TestRepository) {
    fun chooseTest(testName: String): LiveData<String> {
        return testRepository.chooseTest(testName)
    }
}