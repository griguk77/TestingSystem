package data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "text_result",
    foreignKeys = [
        ForeignKey(
            entity = Test::class,
            parentColumns = ["id"],
            childColumns = ["idTest"],
            onDelete = CASCADE
        )]
)
data class TextResult(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idTest: Int,
    val beginPoint: Int,
    val endPoint: Int,
    val text: String
)
