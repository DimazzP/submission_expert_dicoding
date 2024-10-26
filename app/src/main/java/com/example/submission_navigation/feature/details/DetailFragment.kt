package com.example.submission_navigation.feature.details

import android.os.Bundle
import android.util.Log
import android.util.LogPrinter
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.submission_navigation.databinding.FragmentDetailBinding
import androidx.fragment.app.viewModels
import com.example.core.data.models.ResultUser
import com.example.core.domain.entity.Favorite
import com.example.submission_navigation.R
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var splitInstallManager: SplitInstallManager
    private var _userLocal: Favorite? = null

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        dataName = arguments?.getString("login").toString()
        viewModel.findUser(dataName)
        splitInstallManager = SplitInstallManagerFactory.create(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerData()
        observerData1()
        clickButton()
        Log.d("InstalledModules", splitInstallManager.installedModules.toString())
        if (!splitInstallManager.installedModules.contains("favorite")) {
            binding.btnFavorite.visibility = View.GONE
            binding.btnDownload.visibility = View.VISIBLE
        } else {
            binding.btnFavorite.visibility = View.VISIBLE
            binding.btnDownload.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun clickButton() {
        binding.btnFavorite.setOnClickListener {
            val userViewModel = viewModel.userData.value
            val userData = userViewModel?.login?.let { login ->
                Favorite(login, userViewModel.avatarUrl)
            }
            userData?.let {
                if (_userLocal == null) {
                    viewModel.insertFavorite()
                } else {
                    viewModel.deleteFavorite()
                }
            }
        }
    }

    private fun observerData1() {
        viewModel.userData.observe(viewLifecycleOwner) { user ->
            Glide.with(binding.root.context)
                .load(user.avatarUrl)
                .into(binding.imageView)
            binding.textUsername.text = user.login
            binding.textName.text = user.name ?: "Nama Kosong"
            binding.textEmail.text = user.email ?: "Email Kosong"
            binding.textFollower.text = "Followers: ${user.followers.toString()}"
            binding.textFollowing.text = "Following: ${user.following.toString()}"
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(isLoading)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


    private fun observerData() {
        viewModel.userData.observe(viewLifecycleOwner) { user ->
            Glide.with(binding.root.context)
                .load(user.avatarUrl)
                .into(binding.imageView)
            binding.textUsername.text = user.login
            binding.textName.text = user.name ?: "Nama Kosong"
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { load ->
            showLoading(load)
        }
        viewModel.getFavoriteById(dataName).observe(viewLifecycleOwner) { favorite ->
            _userLocal = favorite
            if (_userLocal == null) {
                val drawable = ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.baseline_favorite_border_32
                )
                binding.btnFavorite.setImageDrawable(drawable)
            } else {
                val drawable =
                    ContextCompat.getDrawable(requireActivity(), R.drawable.baseline_favorite_32)
                binding.btnFavorite.setImageDrawable(drawable)
            }
        }
        viewModel.insertStatus.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultUser.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "User has been added to favorites list",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is ResultUser.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is ResultUser.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Failed to add user to favorites list",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {}
            }
        }
        viewModel.deleteStatus.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ResultUser.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "User has been removed from favorites list",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is ResultUser.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is ResultUser.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Failed to remove user from favorites list",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {}
            }
        }
    }
}