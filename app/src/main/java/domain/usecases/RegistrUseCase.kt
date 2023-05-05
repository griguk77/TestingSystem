package domain.usecases

import domain.models.User
import domain.repository.TestRepository

class RegistrUseCase (private val testRepository: TestRepository) {
    fun registr(user: User) {
        testRepository.registr(user)
    }
}