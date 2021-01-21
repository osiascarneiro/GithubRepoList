package com.osias.githubrepos.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.osias.githubrepos.home.view.adapter.HomeRepositoryAdapter
import com.osias.githubrepos.home.viewmodel.HomeViewModel
import com.osias.home.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        configureAdapter()
        return binding.root
    }

    private fun configureAdapter() {
        val adapter = HomeRepositoryAdapter()
        binding.repositoryList.adapter = adapter

        viewModel.repos.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        })
    }
}