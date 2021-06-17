package com.example.newsapp.model.db

import androidx.room.*
import com.example.newsapp.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInsert(article: Article) : Long

    @Query("SELECT * FROM articles") //aponta para Article com a notação @Entity(tableName = "articles")
    fun getAll(): List<Article>

    @Delete
    suspend fun delete(article: Article)

}