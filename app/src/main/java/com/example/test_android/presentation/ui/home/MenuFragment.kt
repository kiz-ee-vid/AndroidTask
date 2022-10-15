package com.example.test_android.presentation.ui.home

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_android.databinding.FragmentMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private val binding: FragmentMenuBinding by lazy { FragmentMenuBinding.inflate(layoutInflater) }
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var menuAdapter: MenuAdapter
    private val productRecycler: RecyclerView by lazy { binding.productRecycler }

    private lateinit var bannerAdapter: BannerAdapter
    private val bannerRecycler: RecyclerView by lazy { binding.bannerRecycler }

    private lateinit var categoryAdapter: CategoryAdapter
    private val categoryRecycler: RecyclerView by lazy { binding.categoryRecycler }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (viewModel.filteredProductList.value.isNullOrEmpty()) {
            if (checkInternetConnection()) {
                viewModel.getData()
            } else {
                Toast.makeText(context, "No Internet connection", Toast.LENGTH_SHORT).show()
                viewModel.restoreData()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bannerAdapter = BannerAdapter()
        bannerRecycler.adapter = bannerAdapter

        categoryAdapter = CategoryAdapter() {
            viewModel.changeCurrentCategory(it)
            viewModel.filterProductList()
        }
        categoryRecycler.adapter = categoryAdapter

        menuAdapter = MenuAdapter()
        productRecycler.adapter = menuAdapter
        productRecycler.layoutManager = LinearLayoutManager(context)

        viewModel.filteredProductList.observe(viewLifecycleOwner) {
            menuAdapter.addList(it)
            categoryAdapter.addList(viewModel.categoryList, viewModel.currentCategory)
        }

        return binding.root
    }

    private fun checkInternetConnection(): Boolean {
        val connection =
            activity?.applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connection.activeNetwork != null
    }
}