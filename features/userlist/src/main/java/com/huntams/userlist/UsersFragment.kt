package com.huntams.userlist

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.huntams.userlist.databinding.FragmentUsersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UsersFragment : Fragment(R.layout.fragment_users) {
    private val binding by viewBinding(FragmentUsersBinding::bind)
    private val viewModel by viewModels<UsersViewModel>()

    @Inject
    lateinit var usersLoadingAdapter: UsersLoadingAdapter
    @Inject
    lateinit var usersPagingAdapter: UsersPagingAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = usersPagingAdapter.withLoadStateFooter(usersLoadingAdapter)
        lifecycleScope.launch {
            binding.root.setOnRefreshListener {
                viewModel.getUsers()
            }
            viewModel.usersLiveData.observe(viewLifecycleOwner) { result ->
                binding.root.isRefreshing = false
                usersLoadingAdapter.setCallback {
                    viewModel.getUsers()
                }
                usersPagingAdapter.apply {
                    submitData(lifecycle, result)
                    setCallback {
                        val deepLink = NavDeepLinkRequest.Builder
                            .fromUri("features://details/${it.infoPage.seed}".toUri())
                            .build()
                        findNavController().navigate(deepLink)
                    }
                }
            }

            binding.recyclerView.adapter = adapter
        }
    }


}
