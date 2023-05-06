package data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test")
data class Test(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val declTest: String
)