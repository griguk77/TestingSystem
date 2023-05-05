package domain.usecases

import domain.models.User
import domain.repository.TestRepository

class LoginUseCase (private val testRepository: TestRepository) {
    fun login(user: User) {
        testRepository.login(user)
    }
}