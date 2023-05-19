package domain.usecases

import domain.repository.TestRepository

class OpenCatalogUseCase(private val testRepository: TestRepository) {
    suspend fun openCatalog(): List<String> {
        return testRepository.openCatalog()
    }
}