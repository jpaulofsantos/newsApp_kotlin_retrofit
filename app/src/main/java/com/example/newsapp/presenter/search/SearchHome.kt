package com.example.newsapp.presenter.search

import com.example.newsapp.model.NewsResponse

interface SearchHome {

    interface Presenter {
        fun search(term: String)
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()
    }
}