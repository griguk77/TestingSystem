package data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "question",
    foreignKeys = [
        ForeignKey(
            entity = Test::class,
            parentColumns = ["name"],
            childColumns = ["testName"],
            onDelete = CASCADE
        )]
)
data class Question(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val testName: String,
    val queNum: Int,
    val queText: String,
    val ans1: String,
    val ans2: String,
    val ans3: String,
    val ans4: String,
    val point1: Int,
    val point2: Int,
    val point3: Int,
    val point4: Int
)
