package ru.ovd.fitness.core.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.ovd.fitness.core.data.entity.AgeGroup
import ru.ovd.fitness.core.data.entity.Exercise
import ru.ovd.fitness.core.data.entity.ExerciseScore
import ru.ovd.fitness.core.data.entity.FitnessLevel
import ru.ovd.fitness.core.data.entity.PassingScore
import ru.ovd.fitness.core.data.entity.QualificationScore

@Database(
    entities = [
        AgeGroup::class,
        FitnessLevel::class,
        PassingScore::class,
        QualificationScore::class,
        Exercise::class,
        ExerciseScore::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fitness_ovd.db"
                )
                    .addCallback(DatabaseCallback(context))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback(
        private val context: Context
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            CoroutineScope(Dispatchers.IO).launch {
                val dao = getDatabase(context).dao()

                dao.insertAgeGroups(ageGroupsData)
                dao.insertFitnessLevels(fitnessLevelsData)
                dao.insertPassingScores(passingScoresData)
                dao.insertQualifications(qualificationsData)
                dao.insertExercises(exercisesData)

                val allExerciseScores = mutableListOf<ExerciseScore>()
                allExerciseScores.addAll(exerciseScoresMenData)
                allExerciseScores.addAll(exerciseScoresWomenData)
                dao.insertExerciseScores(allExerciseScores)
            }
        }
    }
}
