package com.androidgt.blogapp.domain

import com.androidgt.blogapp.core.Result
import com.androidgt.blogapp.data.model.Post

interface HomeScreenRepo {
    suspend fun getLatestPosts(): Result<List<Post>>
}

