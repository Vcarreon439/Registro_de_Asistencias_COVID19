package com.example.registrodeasistenciascovid_19.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.registrodeasistenciascovid_19.Dao.CarreraDao
import com.example.registrodeasistenciascovid_19.Dao.MateriaDao
import com.example.registrodeasistenciascovid_19.Dao.SemestreDao
import com.example.registrodeasistenciascovid_19.entities.*
import java.net.InetSocketAddress

@Database(entities = [Carrera::class, Semestre::class, Materia::class], version = 2, exportSchema = false)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun carreraDao(): CarreraDao
    abstract fun semestreDao(): SemestreDao
    abstract fun materiaDao(): MateriaDao

    companion object{
        @Volatile
        private var INSTANCIA: LocalDatabase? = null

        fun getDatabase(context: Context): LocalDatabase{
            val tempInstance = INSTANCIA

            if (tempInstance!=null)
                return tempInstance

            synchronized(this){
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "localDB"
                ).createFromAsset("database/carrera.db").fallbackToDestructiveMigration().build()
                return instancia
            }
        }
    }
}