package com.example.newsapp.presenter.favorites

import com.example.newsapp.model.Article
import com.example.newsapp.model.data.NewsDataSource
import com.example.newsapp.presenter.ViewHome
import javax.sql.DataSource

class FavoritePresenter(
    private val dataSource: NewsDataSource,
    val view: ViewHome.Favorite): FavoritesHome.Presenter {

    fun getAll() {
        this.dataSource.getAllArticles(this)
    }

    fun saveArticle(article: Article) {
        this.dataSource.saveArticle(article)
    }

    override fun onSucess(articles: List<Article>) {
        this.view.showArticles(articles)
    }

    fun deleteArticle(article: Article) {
        this.dataSource.deleteArticle(article)
    }
}