package domain.usecases

import androidx.lifecycle.LiveData
import domain.repository.TestRepository

class OpenCatalogUseCase (private val testRepository: TestRepository) {
    suspend fun openCatalog(): List<String> {
        return testRepository.openCatalog()
    }
}