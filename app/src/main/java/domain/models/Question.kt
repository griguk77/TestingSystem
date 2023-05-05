package domain.models

data class Question(
    val num: Int,
    val declQuestion: String,
    val variants: List<String>
)
