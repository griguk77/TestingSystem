package domain.models

data class Question(
    var queNum: Int,
    var queText: String,
    var variants: List<String>,
    var points: List<Double>
)
