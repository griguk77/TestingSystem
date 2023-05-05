package domain.usecases

import domain.models.Result
import domain.models.Test
import domain.repository.TestRepository

class ShowAllResultsUseCase (private val testRepository: TestRepository) {
    fun showAllResults(test: Test): List<Result> {
        return testRepository.showAllResults(test)
    }
}