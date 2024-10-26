package com.example.submission_navigation.feature.homes

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission_navigation.R
import com.example.submission_navigation.databinding.FragmentHomeBinding
import com.example.submission_navigation.feature.main.MainViewModel
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var adapter: HomeAdapter? = null
    private lateinit var splitInstallManager: SplitInstallManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        adapter = HomeAdapter { user ->
            val mBundle = Bundle()
            mBundle.putString("login", user.login)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, mBundle)
        }
        splitInstallManager = SplitInstallManagerFactory.create(requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerviewData()
        observeData()
        setupSearch()
        binding.btnFavorite.setOnClickListener {
            installFavoriteModule()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun installFavoriteModule() {
        if (!splitInstallManager.installedModules.contains("favorite")) {
            val request = SplitInstallRequest.newBuilder()
                .addModule("favorite")
                .build()

            splitInstallManager.startInstall(request)
                .addOnSuccessListener {
                    openFavoriteFragment()
                }
                .addOnFailureListener { exception ->
                    Log.e("Error", "Fatal Favorite $exception");
                    // Gagal menginstall module
                    Toast.makeText(
                        context,
                        "Error installing module: ${exception.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        } else {
            openFavoriteFragment()
        }
    }


    private fun openFavoriteFragment() {
        startActivity(
            Intent(
                requireContext(),
                Class.forName("com.example.favorite.feature.favorites.FavoriteActivity")
            )
        )
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.userData.collectLatest { users ->
                adapter?.submitList(users)
                val animation =
                    AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_right)
                val controller = LayoutAnimationController(animation)
                binding.recyclerHome.layoutAnimation = controller
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.isLoading.collectLatest { isLoading ->
                showLoading(isLoading)
            }
        }
    }

    private fun setupSearch() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.hint = searchView.text
                    searchView.hide()
                    viewModel.setUserName(searchView.text.toString())
                    viewModel.findUser()
                    adapter?.clear()
                    true
                }
        }
    }

    private fun recyclerviewData() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerHome.layoutManager = layoutManager
        binding.recyclerHome.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
