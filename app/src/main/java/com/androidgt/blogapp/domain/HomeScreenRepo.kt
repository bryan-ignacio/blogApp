package com.androidgt.blogapp.domain

import com.androidgt.blogapp.core.Resource
import com.androidgt.blogapp.data.model.Post

interface HomeScreenRepo {
    suspend fun getLatestPosts(): Resource<List<Post>>
}

