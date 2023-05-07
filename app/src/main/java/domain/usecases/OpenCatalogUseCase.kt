package domain.usecases

import domain.repository.TestRepository

class OpenCatalogUseCase (private val testRepository: TestRepository) {
    fun openCatalog(): List<String> {
        return testRepository.openCatalog()
    }
}