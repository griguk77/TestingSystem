package domain.models

data class Test(
    val testNameTest: String,
    val declTest: String,
    val countQue: Int,
    val resultPoints: Int,
    val resultText: String,
    val questions: List<Question>
)
