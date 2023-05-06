package domain.usecases

import domain.models.Test
import domain.repository.TestRepository

class OpenCatalogUseCase (private val testRepository: TestRepository) {
    fun openCatalog(): List<Test> {
        return testRepository.openCatalog()
    }
}