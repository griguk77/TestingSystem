package data.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import data.database.AppDatabase
import data.database.TestDao
import domain.models.Question
import domain.models.Result
import domain.models.User
import domain.repository.TestRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TestRepositoryImpl(private val application: Application) : TestRepository {
    private var db = AppDatabase.getInstance(application)

    override fun chooseTest(testName: String): LiveData<String> {
        return db.testDao().getDeclTest(testName)
    }

    override fun continueTest(testName: String, queNum: Int): LiveData<Question> {
        val questionData = db.testDao().getQueInfo(testName, queNum)
        return mapQuestionToDomain(questionData)
    }

    override fun finishTest(testName: String, point: Int, userName: String): LiveData<String> {
        var idResult = 1
        val id = db.testDao().getResultId(testName).value
        if (id != null) {
            idResult = id
        }
        val date = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd.mm.yyyy"))
            .toString()
        db.testDao().insertResult(data.models.Result(idResult, testName, userName, point, date))
        return db.testDao().getTextResult(testName, point)
    }

    override fun login(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun registr(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun showAllResults(testName: String): LiveData<List<Result>> {
        val resultData = db.testDao().getResults(testName)
        return mapResultToDomain(resultData)
    }

    override fun startTest(testName: String): LiveData<Question> {
        val questionData = db.testDao().getQueInfo(testName, 1)
        return mapQuestionToDomain(questionData)
    }

    override fun getCountQue(testName: String): LiveData<Int> {
        return db.testDao().getQueCount(testName)
    }

    override fun openCatalog(): LiveData<List<String>> {
        return db.testDao().getCatalog()
    }

    private fun mapQuestionToDomain(questionData: LiveData<data.models.Question>): LiveData<Question> {
        var questionDomain = MutableLiveData<domain.models.Question>()
        questionDomain = Transformations.map(questionData) {
            questionDomain.value?.queNum = it.queNum
            questionDomain.value?.queText = it.queText
            questionDomain.value?.variants = listOf(it.ans1, it.ans2, it.ans3, it.ans4)
            questionDomain.value?.points = listOf(it.point1, it.point2, it.point3, it.point4)
        } as MutableLiveData<Question>
        return questionDomain
    }

    private fun mapResultToDomain(resultData: LiveData<List<data.models.Result>>): LiveData<List<domain.models.Result>> {
        val resultDomain = MutableLiveData<MutableList<domain.models.Result>>()
        if (resultData.value?.size != null) {
            for (i in 0 until resultData.value?.size!!) {
                val resDomain = resultData.value?.get(i)?.let { domain.models.Result(it.testName, it.user, it.points, it.date) }
                if (resDomain != null) {
                    resultDomain.value?.add(resDomain)
                }
            }
        }
        return resultDomain as MutableLiveData<List<Result>>
    }
}