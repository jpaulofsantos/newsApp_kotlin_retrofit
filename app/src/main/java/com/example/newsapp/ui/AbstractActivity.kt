package com.example.newsapp.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

//classe que tem implem. o AppCompatActivity / onCreate
abstract class AbstractActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        onInject() //injeção dos datasource
    }

    //pega o layout correspondente
    @LayoutRes //garante que retorna um inteiro ao arquivo de layout
    protected abstract fun getLayout(): Int

    //injeção de dep (presenter e datasource)
    protected abstract fun onInject()
}