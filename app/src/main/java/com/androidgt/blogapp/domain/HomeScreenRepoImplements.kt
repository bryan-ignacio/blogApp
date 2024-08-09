package com.androidgt.blogapp.domain

import com.androidgt.blogapp.core.Resource
import com.androidgt.blogapp.data.model.Post
import com.androidgt.blogapp.data.remote.home.HomeScreenDataSource

class HomeScreenRepoImplements(private val dataSource: HomeScreenDataSource): HomeScreenRepo {
    override suspend fun getLatestPosts(): Resource<List<Post>> = dataSource.getLatesPosts()
}