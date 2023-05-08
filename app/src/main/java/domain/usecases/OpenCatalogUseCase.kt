package domain.usecases

import androidx.lifecycle.LiveData
import domain.repository.TestRepository

class OpenCatalogUseCase (private val testRepository: TestRepository) {
    fun openCatalog(): LiveData<List<String>> {
        return testRepository.openCatalog()
    }
}