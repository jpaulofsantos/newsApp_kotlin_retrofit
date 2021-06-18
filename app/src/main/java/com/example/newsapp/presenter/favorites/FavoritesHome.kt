package com.example.newsapp.presenter.favorites

import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsResponse

interface FavoritesHome {

    interface Presenter {
        fun onSucess(articles: List<Article>)
    }
}