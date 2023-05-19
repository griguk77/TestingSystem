package data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "text_result",
    foreignKeys = [
        ForeignKey(
            entity = Test::class,
            parentColumns = ["name"],
            childColumns = ["testName"],
            onDelete = CASCADE
        )]
)
data class TextResult(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val testName: String,
    val beginPoint: Int,
    val endPoint: Int,
    val text: String
)
