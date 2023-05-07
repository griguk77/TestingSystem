package domain.models

data class Question(
    val queNum: Int,
    val queText: String,
    val variants: List<String>,
    val points: List<Int>
)
