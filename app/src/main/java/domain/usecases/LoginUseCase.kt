package domain.usecases

import domain.models.User
import domain.repository.TestRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase (private val testRepository: TestRepository) {
    fun login(user: User): Flow<Boolean> {
        return testRepository.login(user)
    }
}