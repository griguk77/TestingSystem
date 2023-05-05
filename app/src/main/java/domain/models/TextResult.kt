package domain.models

data class TextResult(
    val testName: String,
    val beginPoint: Int,
    val endPoint: Int,
    val text: String
)
