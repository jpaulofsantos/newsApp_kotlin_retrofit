package com.example.newsapp.presenter.news

import com.example.newsapp.model.NewsResponse
import com.example.newsapp.model.data.NewsDataSource
import com.example.newsapp.presenter.ViewHome

class NewsPresenter(
    val view: ViewHome.View,
    private val dataSource: NewsDataSource) : NewsHome.Presenter {

    override fun requestAll() {
        TODO("Not yet implemented")
    }

    override fun onSuccess(newsResponse: NewsResponse) {
        TODO("Not yet implemented")
    }

    override fun onError(message: String) {
        TODO("Not yet implemented")
    }

    override fun onComplete() {
        TODO("Not yet implemented")
    }
}