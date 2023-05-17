package domain.models

//вероятно не будет использоваться
data class TextResult(
    val testName: String,
    val beginPoint: Int,
    val endPoint: Int,
    val text: String
)
