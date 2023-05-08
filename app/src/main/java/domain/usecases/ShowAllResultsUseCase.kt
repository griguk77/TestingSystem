package domain.usecases

import androidx.lifecycle.LiveData
import domain.models.Result
import domain.repository.TestRepository

class ShowAllResultsUseCase (private val testRepository: TestRepository) {
    fun showAllResults(testName: String): LiveData<List<Result>> {
        return testRepository.showAllResults(testName)
    }
}