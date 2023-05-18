package data.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import data.database.AppDatabase
import data.models.Test
import data.models.TextResult
import domain.models.Question
import domain.models.Result
import domain.models.User
import domain.repository.TestRepository
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TestRepositoryImpl(private val application: Application, private val activity: FragmentActivity) : TestRepository {
    private var auth = FirebaseAuth.getInstance()
    private var db = AppDatabase.getInstance(application)

    override suspend fun chooseTest(testName: String): String {
        return db.testDao().getDeclTest(testName)
    }

    override suspend fun continueTest(testName: String, queNum: Int): Question {
        val questionData = db.testDao().getQueInfo(testName, queNum)
        return mapQuestionToDomain(questionData)
    }

    override suspend fun finishTest(testName: String, point: Int, userName: String): String {
        val date = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            .toString()
        db.testDao().insertResult(data.models.Result(0, testName, userName, point, date))
        return db.testDao().getTextResult(testName, point)
    }

    override fun login(user: User): Boolean {
        var success = false
        auth.signInWithEmailAndPassword(user.name, user.password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    success = true
                    Log.d("RRR", "success = $success")
                } else {
                    Log.d("RRR", "не успех")
                }
            }
        Log.d("RRR", success.toString())
        return success
    }

    override fun registr(user: User): Boolean {
        var success = false
        auth.createUserWithEmailAndPassword(user.name, user.password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    Log.d("RRR", "успех")
                    success = true
                } else {
                    Log.d("RRR", "не успех")
                }
            }
        Log.d("RRR", success.toString())
        return success
    }

    override suspend fun showAllResults(testName: String): List<Result> {
        val resultData = db.testDao().getResults(testName)
        val list = ArrayList<domain.models.Result>()
        for (i in resultData.indices) {
            list.add(mapResultToDomain(resultData[i]))
        }
        return list
    }

    override suspend fun getCountQue(testName: String): Int {
        return db.testDao().getQueCount(testName)
    }

    private fun mapQuestionToDomain(questionData: data.models.Question): Question {
        return Question(
            questionData.queNum,
            questionData.queText,
            listOf(
                questionData.ans1,
                questionData.ans2,
                questionData.ans3,
                questionData.ans4
            ),
            listOf(
                questionData.point1,
                questionData.point2,
                questionData.point3,
                questionData.point4
            )
        )
    }

    private fun mapResultToDomain(resultData: data.models.Result): domain.models.Result {
        return domain.models.Result(
            resultData.testName,
            resultData.user,
            resultData.points,
            resultData.date
        )
    }

    override suspend fun openCatalog(): List<String> {
        if (db.testDao().getCatalog().isEmpty()) {
            //Log.d("RRR", "пусто")
            db.testDao().insertTest(
                Test(
                    "Математика",
                    "Данный тест позволит оценить Ваши навыки в математике",
                    8
                )
            )
            db.testDao().insertTest(
                Test(
                    "Русский язык",
                    "Данный тест позволит оценить уровень Вашего владения русским языком",
                    8
                )
            )
            db.testDao().insertTest(
                Test(
                    "История",
                    "Данный тест позволит оценить Ваши знания истории",
                    8
                )
            )
            db.testDao().insertTest(
                Test(
                    "Химия",
                    "Данный тест позволит оценить Ваши знания о химии",
                    8
                )
            )
            db.testDao().insertTest(
                Test(
                    "Биология",
                    "Данный тест позволит оценить Ваш уровень знаний биологии",
                    8
                )
            )
            db.testDao().insertTest(
                Test(
                    "Политические координаты",
                    "Данный тест позволит оценить Вашу политическую предрасположенность",
                    8
                )
            )

            db.testDao().insertQuestion(
                data.models.Question(
                    1,
                    "Математика",
                    1,
                    "Биссектриса - это ...",
                    "Прямая, делящая угол пополам",
                    "Прямая, делящая сторону пополам",
                    "Центр пересечения перпендикуляров",
                    "Связанный набор параметров",
                    12.5,
                    -4.16,
                    -4.17,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    2,
                    "Математика",
                    2,
                    "Медиана - это ...",
                    "Прямая, делящая угол пополам",
                    "Прямая, делящая сторону пополам",
                    "Центр пересечения перпендикуляров",
                    "Связанный набор параметров",
                    -4.16,
                    12.5,
                    -4.17,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    3,
                    "Математика",
                    3,
                    "2 + 2 * 2 = ?",
                    "2",
                    "4",
                    "6",
                    "8",
                    -4.16,
                    -4.17,
                    12.5,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    4,
                    "Математика",
                    4,
                    "(2 + 2) * 2 = ?",
                    "2",
                    "4",
                    "6",
                    "8",
                    -4.16,
                    -4.17,
                    -4.17,
                    12.5
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    5,
                    "Математика",
                    5,
                    "Два пешехода вышли одновременно из одного и того же пункта в " +
                            "противоположных направлениях. Скорость первого пешехода 3 км/ч, " +
                            "второго - 4 км/ч. Какое расстояние между ними будет через 2 ч?",
                    "12",
                    "14",
                    "16",
                    "18",
                    -4.16,
                    12.5,
                    -4.17,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    6,
                    "Математика",
                    6,
                    "У мамы 2 яблока и 3 груши. Каждый день в течение 5 дней подряд она " +
                            "выдает по одному фрукту. Сколькими способами это может быть сделано?",
                    "5",
                    "7",
                    "10",
                    "11",
                    -4.16,
                    -4.17,
                    12.5,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    7,
                    "Математика",
                    7,
                    "Найдите логарифм числа 2048 по основанию 2",
                    "11",
                    "12",
                    "13",
                    "15",
                    12.5,
                    -4.16,
                    -4.17,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    8,
                    "Математика",
                    8,
                    "Сколько корней имеет квадратное уравнение, дискриминант которого" +
                            " равен нулю?",
                    "0",
                    "1",
                    "2 разных",
                    "2 одинаковых",
                    -4.16,
                    -4.17,
                    -4.17,
                    12.5
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    9,
                    "Русский язык",
                    1,
                    "Укажите слова, в которых заглавная буква является ударной",
                    "ждАла",
                    "ободралА",
                    "созЫв",
                    "намЕрение",
                    -12.5,
                    4.16,
                    4.17,
                    4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    10,
                    "Русский язык",
                    2,
                    "Укажите варианты, в которых у выделенных слов форма образована неверно",
                    "об АЭРОПОРТЕ",
                    "по ИХ указанию",
                    "ЕДЬ побыстрее",
                    "уважаемые ДИРЕКТОРА",
                    -4.16,
                    -4.17,
                    12.5,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    11,
                    "Русский язык",
                    3,
                    "На хозяине была тка(1)ая рубаха, подпояса(2)ая кожа(3)ым ремнём, и " +
                            "давно не глаже(4)ые штаны. На месте каких цифр нужно поставить Н?",
                    "1",
                    "2",
                    "3",
                    "4",
                    6.25,
                    -6.25,
                    6.25,
                    -6.25
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    12,
                    "Русский язык",
                    4,
                    "Пространство напротив (1) находившегося между крыльцом и глухой " +
                            "стеной окна (2) было забрано высокими досками (3) позволявшими " +
                            "видеть (4) только небольшой клочок неба. На месте каких цифр нужно " +
                            "поставить запятую?",
                    "1",
                    "2",
                    "3",
                    "4",
                    -4.16,
                    -4.17,
                    12.5,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    13,
                    "Русский язык",
                    5,
                    "Кабинет представлял собой высокую угловую комнату, выходившую двумя" +
                            " окнами в тенистый сад (1) из-за разорванной линии (2) которого (3)" +
                            " виднелись полоса заводского пруда (4) и контуры грудившихся гор. На" +
                            " месте каких цифр нужно поставить запятую?",
                    "1",
                    "2",
                    "3",
                    "4",
                    12.5,
                    -4.16,
                    -4.17,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    14,
                    "Русский язык",
                    6,
                    "Я не знал (1) сколько времени бродил по лесам (2) и (3) когда вернулся в дом лесника (4) оказалось, что меня там уже давно ждут. На месте каких цифр нужно поставить запятую?",
                    "1",
                    "2",
                    "3",
                    "4",
                    3.125,
                    3.125,
                    3.125,
                    3.125
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    15,
                    "Русский язык",
                    7,
                    "Ставится ли тире после местоимений?",
                    "Да",
                    "Нет",
                    "Зависит от ситуации",
                    "Такой знак отсутствует",
                    -4.16,
                    12.5,
                    -4.17,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    16,
                    "Русский язык",
                    8,
                    "Какие слова пишутся с двойной буквой Н?",
                    "Стеклян..ый",
                    "Оловян..ый",
                    "Жарен..ый",
                    "Пожарен..ый",
                    4.16,
                    4.17,
                    -12.5,
                    4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    17,
                    "История",
                    1,
                    "Назовите дату Советско-финской “зимней” войны.",
                    "1937г.",
                    "1935-1939гг.",
                    "1940г.",
                    "1939-1940гг.",
                    -4.16,
                    -4.17,
                    -4.17,
                    12.5
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    18,
                    "История",
                    2,
                    "В Первой мировой войне принимали участие сколько стран?",
                    "49",
                    "38",
                    "28",
                    "22",
                    -4.16,
                    12.5,
                    -4.17,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    19,
                    "История",
                    3,
                    "Мировой экономический кризис в США имел название",
                    "Американский кризис",
                    "Великая депрессия",
                    "Чёрный день",
                    "Период стагнации",
                    -4.16,
                    12.5,
                    -4.17,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    20,
                    "История",
                    4,
                    "Назовите автора скульптуры «Родина-мать зовет!»",
                    "Евгений Вучетич",
                    "Вера Мухина",
                    "Вячеслав Клыков",
                    "Зураб Церетели",
                    12.5,
                    -4.16,
                    -4.17,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    21,
                    "История",
                    5,
                    "Кто занял пост Генерального секретаря ЦК КПСС после Андропова?",
                    "Р.С.Кочубей",
                    "Е.Б.Лихачев",
                    "Д.Б.Умаров",
                    "К.У.Черненко",
                    -4.16,
                    -4.17,
                    -4.17,
                    12.5
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    22,
                    "История",
                    6,
                    "Назовите дату начала Второй Мировой войны",
                    "1 сентября 1939г.",
                    "2 сентября 1945г.",
                    "22 июня 1941г.",
                    "23 февраля 1941г.",
                    12.5,
                    -4.16,
                    -4.17,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    23,
                    "История",
                    7,
                    "Назовите изначальных членов Антанты в Первой Мировой войне",
                    "Российская империя",
                    "Великобритания",
                    "США",
                    "Франция",
                    4.16,
                    4.17,
                    -12.5,
                    4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    24,
                    "История",
                    8,
                    "Назовите дату распада СССР",
                    "31 декабря 1991г.",
                    "26 декабря 1990г.",
                    "26 декабря 1991г.",
                    "1 января 1992г.",
                    -4.16,
                    -4.17,
                    12.5,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    25,
                    "Химия",
                    1,
                    "Для отделения спирта от воды можно использовать",
                    "выпаривание",
                    "отстаивание",
                    "дистилляцию",
                    "фильтрование",
                    -4.16,
                    -4.17,
                    12.5,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    26,
                    "Химия",
                    2,
                    "Для выделения поваренной соли из ее смеси с песком и другими " +
                            "нерастворимыми примесями можно использовать",
                    "растворение",
                    "возгонку",
                    "фильтрование",
                    "выпаривание",
                    4.16,
                    -12.5,
                    4.17,
                    4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    27,
                    "Химия",
                    3,
                    "На водоочистных станциях в качестве фильтра используют",
                    "сито",
                    "песок",
                    "бумагу",
                    "уголь",
                    -4.16,
                    12.5,
                    -4.17,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    28,
                    "Химия",
                    4,
                    "Укажите химическое явление",
                    "растворение сахара",
                    "испарение воды",
                    "разложение сернистой кислоты",
                    "плавление льда",
                    -4.16,
                    -4.17,
                    12.5,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    29,
                    "Химия",
                    5,
                    "Укажите физическое явление",
                    "перегонка нефти",
                    "горение угля",
                    "синтез аммиака",
                    "разложение угольной кислоты",
                    12.5,
                    -4.16,
                    -4.17,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    30,
                    "Химия",
                    6,
                    "Среди нижеперечисленных методов получения чистых веществ укажите те," +
                            " которые относят к физическим явлениям:",
                    "дистилляция",
                    "фильтрование",
                    "кристаллизация",
                    "возгонка",
                    3.125,
                    3.125,
                    3.125,
                    3.125
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    31,
                    "Химия",
                    7,
                    "Что верно?\nА. При физических явлениях состав вещества не меняется," +
                            " а изменяется агрегатное состояние вещества или форма и размеры " +
                            "тел.\nБ. К физическим явлениям относятся: перегонка нефти и " +
                            "дистилляция воды. которые относят к физическим явлениям:",
                    "только А",
                    "только Б",
                    "оба верны",
                    "оба неверны",
                    -4.16,
                    -4.17,
                    12.5,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    32,
                    "Химия",
                    8,
                    "Что можно узнать с помощью порядкового номера химического элемента?",
                    "заряд ядра",
                    "количество протонов",
                    "количество электронов",
                    "валентность",
                    4.16,
                    4.17,
                    4.17,
                    -12.5
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    33,
                    "Биология",
                    1,
                    "Уровень организации живой природы, представляющий собой совокупность" +
                            " всех экосистем Земли в их взаимосвязи, называется:",
                    "популяционный",
                    "биосферный",
                    "молекулярный",
                    "организменный",
                    -4.16,
                    12.5,
                    -4.17,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    34,
                    "Биология",
                    2,
                    "Уровень, на котором начинает проявляться способность живых систем к" +
                            " обмену веществ, энергии, информации, является:",
                    "популяционно-видовым",
                    "биосферным",
                    "клеточным",
                    "организменным",
                    -4.16,
                    -4.17,
                    12.5,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    35,
                    "Биология",
                    3,
                    "Высшим уровнем организации жизни является:",
                    "биогеоценотический",
                    "биосферный",
                    "молекулярный",
                    "клеточный",
                    -4.16,
                    12.5,
                    -4.17,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    36,
                    "Биология",
                    4,
                    "Основное отличие прокариот от эукариот связано с отсутствием у " +
                            "прокариот:",
                    "рибосом",
                    "ДНК",
                    "клеточного строения",
                    "настоящего ядра",
                    -4.16,
                    -4.17,
                    -4.17,
                    12.5
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    37,
                    "Биология",
                    5,
                    "Функцию хранения генетической информации в эукариотической клетке " +
                            "выполняет(ют):",
                    "цитоплазма клетки",
                    "хромосомы ядра",
                    "нуклеоид",
                    "ядрышко",
                    -4.16,
                    12.5,
                    -4.17,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    38,
                    "Биология",
                    6,
                    "У прокариотической клетки есть:",
                    "рибосомы",
                    "нуклеоид с ДНК",
                    "клеточная мембрана",
                    "комплекс Гольджи",
                    4.16,
                    4.17,
                    4.17,
                    -12.5
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    39,
                    "Биология",
                    7,
                    "Источником энергии, выделяющейся при гликолизе, является:",
                    "белок",
                    "АТФ",
                    "глюкоза",
                    "жир",
                    -4.16,
                    -4.17,
                    12.5,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    40,
                    "Биология",
                    8,
                    "Какое из царств относится к прокариотам?",
                    "вирусы",
                    "бактерии",
                    "грибы",
                    "животные",
                    -4.16,
                    12.5,
                    -4.17,
                    -4.17
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    41,
                    "Политические координаты",
                    1,
                    "Как Вы относитесь к частной собственности?",
                    "резко положительно",
                    "умеренно положительно",
                    "умеренно отрицательно",
                    "резко отрицательно",
                    12.5,
                    6.25,
                    -6.25,
                    -12.5
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    42,
                    "Политические координаты",
                    2,
                    "Интересы личности должны быть выше интересов общества?",
                    "да",
                    "возможно, да",
                    "возможно нет",
                    "нет",
                    12.5,
                    6.25,
                    -6.25,
                    -12.5
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    43,
                    "Политические координаты",
                    3,
                    "Как Вы относитесь к равенству социальных групп?",
                    "резко положительно",
                    "умеренно положительно",
                    "умеренно отрицательно",
                    "резко отрицательно",
                    12.5,
                    6.25,
                    -6.25,
                    -12.5
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    44,
                    "Политические координаты",
                    4,
                    "Насколько сильно государство должно влиять на жизнь людей в стране?",
                    "практически никак",
                    "умеренно",
                    "сильно",
                    "на все сферы жизни",
                    12.5,
                    6.25,
                    -6.25,
                    -12.5
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    45,
                    "Политические координаты",
                    5,
                    "Кто должен платить больше налогов: бедные или богатые?",
                    "бедные",
                    "одинаково",
                    "богатые",
                    "затрудняюсь ответить",
                    12.5,
                    6.25,
                    -12.5,
                    -6.25
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    46,
                    "Политические координаты",
                    6,
                    "Что для вас важнее?",
                    "свобода",
                    "справедливость",
                    "безопасность",
                    "полный порядок",
                    12.5,
                    6.25,
                    -6.25,
                    -12.5
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    47,
                    "Политические координаты",
                    7,
                    "Что Вы бы сделали в первую очередь, если стали президентом?",
                    "привнёс демократию",
                    "снизил налоги",
                    "усилил армию",
                    "укрепил власть",
                    12.5,
                    6.25,
                    -6.25,
                    -12.5
                )
            )
            db.testDao().insertQuestion(
                data.models.Question(
                    48,
                    "Политические координаты",
                    8,
                    "Какой политический лидер Вам более симпатичен?",
                    "Макрон",
                    "Трамп",
                    "Меркель",
                    "Сталин",
                    12.5,
                    6.25,
                    -6.25,
                    -12.5
                )
            )

            db.testDao().insertTextResult(
                TextResult(
                    1,
                    "Математика",
                    -100,
                    25,
                    "Ваши знания крайне слабы, Вам нужно лучше изучить предмет и пройти тест" +
                            " повторно"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    2,
                    "Математика",
                    25,
                    50,
                    "Вам знаком некоторый материал данной дисциплины, но чтобы считать себя" +
                            " знатоком придётся узнать ещё крайне много информации"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    3,
                    "Математика",
                    50,
                    75,
                    "Вы достаточно хорошо владеете материалом и знаете ответы на многие " +
                            "вопросы дисциплины"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    4,
                    "Математика",
                    75,
                    101,
                    "Ваши знания предмета великолепны, так держать!"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    5,
                    "Русский язык",
                    -100,
                    25,
                    "Ваши знания крайне слабы, Вам нужно лучше изучить предмет и пройти тест" +
                            " повторно"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    6,
                    "Русский язык",
                    25,
                    50,
                    "Вам знаком некоторый материал данной дисциплины, но чтобы считать себя" +
                            " знатоком придётся узнать ещё крайне много информации"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    7,
                    "Русский язык",
                    50,
                    75,
                    "Вы достаточно хорошо владеете материалом и знаете ответы на многие " +
                            "вопросы дисциплины"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    8,
                    "Русский язык",
                    75,
                    101,
                    "Ваши знания предмета великолепны, так держать!"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    9,
                    "История",
                    -100,
                    25,
                    "Ваши знания крайне слабы, Вам нужно лучше изучить предмет и пройти тест" +
                            " повторно"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    10,
                    "История",
                    25,
                    50,
                    "Вам знаком некоторый материал данной дисциплины, но чтобы считать себя" +
                            " знатоком придётся узнать ещё крайне много информации"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    11,
                    "История",
                    50,
                    75,
                    "Вы достаточно хорошо владеете материалом и знаете ответы на многие " +
                            "вопросы дисциплины"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    12,
                    "История",
                    75,
                    101,
                    "Ваши знания предмета великолепны, так держать!"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    13,
                    "Химия",
                    -100,
                    25,
                    "Ваши знания крайне слабы, Вам нужно лучше изучить предмет и пройти тест" +
                            " повторно"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    14,
                    "Химия",
                    25,
                    50,
                    "Вам знаком некоторый материал данной дисциплины, но чтобы считать себя" +
                            " знатоком придётся узнать ещё крайне много информации"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    15,
                    "Химия",
                    50,
                    75,
                    "Вы достаточно хорошо владеете материалом и знаете ответы на многие " +
                            "вопросы дисциплины"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    16,
                    "Химия",
                    75,
                    101,
                    "Ваши знания предмета великолепны, так держать!"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    17,
                    "Биология",
                    -100,
                    25,
                    "Ваши знания крайне слабы, Вам нужно лучше изучить предмет и пройти тест" +
                            " повторно"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    18,
                    "Биология",
                    25,
                    50,
                    "Вам знаком некоторый материал данной дисциплины, но чтобы считать себя" +
                            " знатоком придётся узнать ещё крайне много информации"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    19,
                    "Биология",
                    50,
                    75,
                    "Вы достаточно хорошо владеете материалом и знаете ответы на многие " +
                            "вопросы дисциплины"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    20,
                    "Биология",
                    75,
                    101,
                    "Ваши знания предмета великолепны, так держать!"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    21,
                    "Политические координаты",
                    -100,
                    -50,
                    "Вам близки такие течения, как марксизм, сталинизм, чучхе, авторитарный" +
                            " социализм, а приоритетом для Вас являются порядок и сила государства"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    22,
                    "Политические координаты",
                    -50,
                    0,
                    "Вам близки такие течения, как консерватизм, традиционализм, " +
                            "национализм, монархизм, Вы ближе к центризму, а приоритетом для Вас" +
                            " являются безопасность и суверенитет государства"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    23,
                    "Политические координаты",
                    0,
                    50,
                    "Вам близки такие течения, как умеренный либерализм, также Вам близок " +
                            "капитализм, Вы ближе к центризму, а приоритетом для Вас являются " +
                            "право частной собственности и материальное благосостояние"
                )
            )
            db.testDao().insertTextResult(
                TextResult(
                    24,
                    "Политические координаты",
                    50,
                    101,
                    "Вам близки такие течения, как классический либерализм, неолиберализм, " +
                            "либертарианство, а приоритетом для Вас являются свобода и равенство" +
                            " человека"
                )
            )
        } else {
            //Log.d("RRR", "не пусто")
        }
        return db.testDao().getCatalog()
    }
}