package com.example.newsapp.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class) //informa ao Room para realizar a conversão
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {

        @Volatile //indica que as gravações neste campo ficam visiveis imediatamente a outros encadeamentos
        private var instance: ArticleDatabase? = null //instancia do BD artigos
        private val lock = Any() //garante que existe apenas uma instancia do bd

        //sempre chamado ao criar uma instância do bd
        operator fun invoke(context: Context) = instance ?: synchronized(lock) {  //synchronized garante que não seja acessado por outras threads ao mesmo tempo
            instance ?: createdDatabase(context).also { articleDatabase ->  
                instance = articleDatabase
            }
        }

        private fun createdDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}