package data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.models.Question
import data.models.Result
import data.models.Test
import data.models.TextResult

@Dao
interface TestDao {
    @Query("SELECT declTest FROM test WHERE name == :testName LIMIT 1")
    suspend fun getDeclTest(testName: String): String

    @Query("SELECT * FROM question WHERE testName == :testName AND queNum == :queNum LIMIT 1")
    fun getQueInfo(testName: String, queNum: Int): LiveData<Question>

    @Query("SELECT text FROM text_result WHERE testName == :testName AND beginPoint <= :point AND endPoint > :point LIMIT 1")
    fun getTextResult(testName: String, point: Double): LiveData<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: Result)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTest(test: Test)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTextResult(textResult: TextResult)

    @Query("SELECT * FROM result WHERE testName == :testName ORDER BY id DESC")
    fun getResults(testName: String): LiveData<List<Result>>

    @Query("SELECT name FROM test ORDER BY name")
    suspend fun getCatalog(): List<String>

    @Query("SELECT queCount FROM test WHERE name == :testName LIMIT 1")
    fun getQueCount(testName: String): LiveData<Int>

    @Query("SELECT id FROM result WHERE testName == :testName ORDER BY id DESC LIMIT 1")
    fun getResultId(testName: String): LiveData<Int>
}