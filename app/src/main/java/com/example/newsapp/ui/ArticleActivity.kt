package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.newsapp.R
import com.example.newsapp.model.Article
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AbstractActivity() {

    private lateinit var article: Article

    override fun getLayout(): Int = R.layout.activity_article

    override fun onInject() {

        getArticle()
        webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { url ->
                loadUrl(url)
            }
        }
    }

    private fun getArticle() {
        intent.extras?.let {
            article = it.get("article") as Article
        }
    }
}