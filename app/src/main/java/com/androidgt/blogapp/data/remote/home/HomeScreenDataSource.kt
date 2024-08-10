package com.androidgt.blogapp.data.remote.home

import com.androidgt.blogapp.core.Result
import com.androidgt.blogapp.data.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HomeScreenDataSource {

    suspend fun getLatesPosts(): Result<List<Post>> {
        val postList = mutableListOf<Post>()

        val querySnapshot = FirebaseFirestore.getInstance().collection("posts").get().await()

        for (post in querySnapshot.documents) {
            post.toObject(Post::class.java)?.let { p ->
                postList.add(p)
            }
        }
        return Result.Success(postList)
    }
}