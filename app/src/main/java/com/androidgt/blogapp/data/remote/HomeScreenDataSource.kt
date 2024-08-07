package com.androidgt.blogapp.data.remote

import com.androidgt.blogapp.core.Resource
import com.androidgt.blogapp.data.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HomeScreenDataSource {

    suspend fun getLatesPosts(): Resource<List<Post>> {
        val postList = mutableListOf<Post>()

        val querySnapshot = FirebaseFirestore.getInstance().collection("posts").get().await()

        for (post in querySnapshot.documents) {
            post.toObject(Post::class.java)?.let { p ->
                postList.add(p)
            }
        }
        return Resource.Success(postList)
    }
}