package data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)