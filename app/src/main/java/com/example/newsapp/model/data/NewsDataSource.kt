package com.example.newsapp.model.data

import android.content.Context
import com.example.newsapp.model.Article
import com.example.newsapp.model.db.ArticleDatabase
import com.example.newsapp.network.RetrofitInstance
import com.example.newsapp.presenter.favorites.FavoritesHome
import com.example.newsapp.presenter.news.NewsHome
import com.example.newsapp.presenter.search.SearchHome
import kotlinx.coroutines.*
import retrofit2.Retrofit

class NewsDataSource(context: Context) {

    private var db: ArticleDatabase = ArticleDatabase(context)
    private var newsRepository: NewsRepository = NewsRepository(db)

    fun getBreakingNews(callback: NewsHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val breakingNewsResponse = RetrofitInstance.api.getBreakingNews("br")

            if (breakingNewsResponse.isSuccessful) {
                breakingNewsResponse.body()?.let { newsResponse ->
                    callback.onSuccess(newsResponse)
                }

                callback.onComplete()
            } else {
                callback.onError(breakingNewsResponse.message())
                callback.onComplete()
            }
        }
    }

    fun searchNews(term: String, callback: SearchHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val searchResponse = RetrofitInstance.api.searchNews(term)

            if (searchResponse.isSuccessful) {
                searchResponse.body()?.let { searchResult ->
                    callback.onSuccess(searchResult)
                }

                callback.onComplete()
            } else  {
                callback.onError(searchResponse.message())
                callback.onComplete()
            }
        }

    }

    fun saveArticle(article: Article) {
        GlobalScope.launch(Dispatchers.Main) {
            newsRepository.updateInsert(article)
        }
    }

    fun getAllArticles(callback: FavoritesHome) {
        var allArticles: List<Article>
        CoroutineScope(Dispatchers.IO).launch {
            allArticles = newsRepository.getAll()

            withContext(Dispatchers.Main) {
                callback.showArticles(allArticles)
            }
        }
    }

    fun deleteArticle(article: Article) {
        GlobalScope.launch(Dispatchers.Main) {
            newsRepository.delete(article)
        }
    }
}