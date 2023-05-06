package data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "result",
    foreignKeys = [
        ForeignKey(
            entity = Test::class,
            parentColumns = ["name"],
            childColumns = ["testName"],
            onDelete = CASCADE
        )]
)
data class Result(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val testName: String,
    val user: String,
    val points: Int,
    val date: String
)
