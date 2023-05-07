package data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.models.Question
import data.models.Result
import data.models.Test

@Dao
interface TestDao {
    @Query("SELECT declTest FROM test WHERE name == :testName LIMIT 1")
    fun getDeclTest(testName: String): LiveData<String>

    @Query("SELECT * FROM question WHERE testName == :testName AND queNum == :queNum LIMIT 1")
    fun getQueInfo(testName: String, queNum: Int): LiveData<Question>

    @Query("SELECT text FROM text_result WHERE testName == :testName AND beginPoint <= :point AND endPoint > :point LIMIT 1")
    fun getTextResult(testName: String, point: Int): LiveData<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResult(result: Result)

    @Query("SELECT * FROM result WHERE testName == :testName")
    fun getResults(testName: String): LiveData<List<Result>>

    @Query("SELECT name FROM test")
    fun getCatalog(): LiveData<List<String>>

    @Query("SELECT queCount FROM test WHERE name == :testName LIMIT 1")
    fun getQueCount(testName: String): LiveData<Int>
}