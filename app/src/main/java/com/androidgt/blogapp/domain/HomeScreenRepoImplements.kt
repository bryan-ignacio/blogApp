package com.androidgt.blogapp.domain

import com.androidgt.blogapp.core.Result
import com.androidgt.blogapp.data.model.Post
import com.androidgt.blogapp.data.remote.home.HomeScreenDataSource

class HomeScreenRepoImplements(private val dataSource: HomeScreenDataSource): HomeScreenRepo {
    override suspend fun getLatestPosts(): Result<List<Post>> = dataSource.getLatesPosts()
}