package com.androidgt.blogapp.core

// sealed: ya que contiene distintos tipos de output.
sealed class Result<out T> {

    class Loading<out T>: Result<T>()

    data class Success<out T>(val data: T): Result<T>()

    data class Failure(val exception: Exception): Result<Nothing>()



}