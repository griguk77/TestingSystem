package data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Test (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)