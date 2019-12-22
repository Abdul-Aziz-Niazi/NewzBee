package com.az3ez.daos

data class NewsArticle(
    var source: NewsSource,
    var author: String,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var content: String,
    var publishedAt: String
)