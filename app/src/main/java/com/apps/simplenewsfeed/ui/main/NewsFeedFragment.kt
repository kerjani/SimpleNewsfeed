package com.apps.simplenewsfeed.ui.main

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apps.simplenewsfeed.R
import com.apps.simplenewsfeed.databinding.FragmentNewsFeedListBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class NewsFeedFragment : Fragment() {

    private val viewModel: NewsFeedViewModel by viewModels()

    private lateinit var binding: FragmentNewsFeedListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news_feed_list, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.itemList.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                requireContext().resources.getDimensionPixelOffset(R.dimen.item_decoration).let {
                    outRect.apply {
                        bottom = it
                        left = it
                        right = it
                    }
                }
            }
        })
        viewModel.latestItems.observe(viewLifecycleOwner, {
            binding.itemList.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = MyItemRecyclerViewAdapter(it)
            }
        })
        viewModel.error.observe(viewLifecycleOwner, { error ->
            error?.let {
                Snackbar.make(view, error, Snackbar.LENGTH_LONG).show()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.getItems(true)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getItems(false)
    }
}