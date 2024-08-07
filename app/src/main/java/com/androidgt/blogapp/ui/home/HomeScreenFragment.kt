package com.androidgt.blogapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidgt.blogapp.R
import com.androidgt.blogapp.data.model.Post
import com.androidgt.blogapp.databinding.FragmentHomeScreenBinding
import com.androidgt.blogapp.ui.home.adapter.HomeScreenAdapter
import com.google.firebase.Timestamp

class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {

    private lateinit var binding: FragmentHomeScreenBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)

        val postList = listOf(
            Post(
                "https://process.filestackapi.com/cache=expiry:max/resize=width:320/mrFuqDcrT3mXeujn6eEN",
                "Bryan Ignacio",
                Timestamp.now(),
                "https://pbs.twimg.com/media/EkSsEFkXgAIftCu.jpg"
            ),
            Post(
                "https://process.filestackapi.com/cache=expiry:max/resize=width:320/mrFuqDcrT3mXeujn6eEN",
                "Bryan Ignacio",
                Timestamp.now(),
                "https://pbs.twimg.com/media/EkSsEFkXgAIftCu.jpg"
            ),
            Post(
                "https://process.filestackapi.com/cache=expiry:max/resize=width:320/mrFuqDcrT3mXeujn6eEN",
                "Bryan Ignacio",
                Timestamp.now(),
                "https://pbs.twimg.com/media/EkSsEFkXgAIftCu.jpg"
            ),

            )



        binding.rvHome.adapter = HomeScreenAdapter(postList)

    }

}