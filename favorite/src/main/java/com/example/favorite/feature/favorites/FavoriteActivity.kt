package com.example.favorite.feature.favorites

import FavoriteViewModel
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.di.FavoriteModuleDependencies
import com.example.favorite.databinding.ActivityFavoriteBinding
import com.example.submission_navigation.feature.main.MainActivity
import dagger.hilt.android.EntryPointAccessors


class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var favoriteViewModel: FavoriteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        favoriteViewModel = initViewModel()
        observeFavoriteList()
    }

    private fun initViewModel(): FavoriteViewModel {
        val entryPoint = EntryPointAccessors.fromApplication(
            applicationContext,
            FavoriteModuleDependencies::class.java
        )
        val getFavoritesUseCase = entryPoint.getFavoritesUseCase()

        val factory = FavoriteViewModelFactory(getFavoritesUseCase)
        return ViewModelProvider(this, factory)[FavoriteViewModel::class.java]
    }

    private fun setupRecyclerView() {
        favoriteAdapter = FavoriteAdapter { favorite ->
            val deepLinkUri = Uri.parse("myapp://detail/${favorite.username}")
            val intent = Intent(Intent.ACTION_VIEW, deepLinkUri)
            startActivity(intent)
        }
        binding.recyclerFavorite.layoutManager = LinearLayoutManager(this)
        binding.recyclerFavorite.adapter = favoriteAdapter
    }

    private fun observeFavoriteList() {
        favoriteViewModel.favoriteList.observe(this) { list ->
                favoriteAdapter.submitList(list)
        }
    }
}