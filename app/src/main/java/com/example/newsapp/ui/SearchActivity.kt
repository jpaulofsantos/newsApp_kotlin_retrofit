package com.example.newsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.MainAdapter
import com.example.newsapp.model.Article
import com.example.newsapp.model.data.NewsDataSource
import com.example.newsapp.presenter.ViewHome
import com.example.newsapp.presenter.news.NewsPresenter
import com.example.newsapp.presenter.search.SearchPresenter
import com.example.newsapp.utils.UtilQueryTextListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AbstractActivity(), ViewHome.View {

    private val mainAdapter by lazy {
        MainAdapter()
    }

    private lateinit var searchPresenter : SearchPresenter

    override fun getLayout(): Int = R.layout.activity_search

    override fun onInject() {

        val dataSource = NewsDataSource()
        searchPresenter = SearchPresenter(this, dataSource)
        configRecycle()
        search()
        clickAdapter()
    }

    private fun search() {
        searchNews.setOnQueryTextListener(UtilQueryTextListener(this.lifecycle){
            text ->
            text?.let { query ->
                if (query.isNotEmpty()) {
                    searchPresenter.search(query)
                    rvProgressBarSearch.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun configRecycle() {
        with(rvSearchNews) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@SearchActivity, DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun showProgressBar() {
        rvProgressBarSearch.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        rvProgressBarSearch.visibility = View.INVISIBLE
    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }

    private fun clickAdapter() {
        mainAdapter.setOnClickListener { article ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }
    }

}