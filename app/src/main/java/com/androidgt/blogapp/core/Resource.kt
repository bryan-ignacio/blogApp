package com.androidgt.blogapp.core

// sealed: ya que contiene distintos tipos de output.
sealed class Resource<out T> {

    class Loading<out T>: Resource<T>()

    data class Success<out T>(val data: T): Resource<T>()

    data class Failure(val exception: Exception): Resource<Nothing>()



}