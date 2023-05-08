package data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test")
data class Test(
    @PrimaryKey
    val name: String,
    val declTest: String,
    val queCount: Int
)