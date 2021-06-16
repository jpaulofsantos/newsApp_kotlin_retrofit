package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.model.Article
import kotlinx.android.synthetic.main.item_news.view.*

class MainAdapter : RecyclerView.Adapter<MainAdapter.ArticleViewHolder>() { //extender de RecyclerView.ViewHolder, criar ArticleViewHolder e passar

    private val diffCallBack = object : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean { //verifica se os itens são os mesmos objetos
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean { //se os itens possuem os mesmos dados
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallBack)

    //inflar o layout item_news, cria a view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_news, parent, false)
        )
    //count no differ
    override fun getItemCount(): Int = differ.currentList.size

    //linkar os dados do item e passar pra ele
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivArticleImage) //carregando a imagem
            tvTitle.text = article.author ?: article.source?.name
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
            tvSource.text = article.source?.name ?: article.author

            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(article)
                }
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null


    fun setOnClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}