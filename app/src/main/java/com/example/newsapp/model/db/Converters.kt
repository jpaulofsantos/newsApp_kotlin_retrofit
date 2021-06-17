package com.example.newsapp.model.db

import androidx.room.TypeConverter
import com.example.newsapp.model.Source

//convertendo o Source em String, pois o Room não interpreta o tipo Source em Article, apenas tipos primitivos, Strings
class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource (name: String) : Source {
        return Source(name, name)
    }

}