package com.apps.simplenewsfeed.ui.main

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.apps.simplenewsfeed.BR
import com.apps.simplenewsfeed.data.models.Item
import com.apps.simplenewsfeed.databinding.ItemNewsBinding
import com.apps.simplenewsfeed.util.AdClickedListener
import com.apps.simplenewsfeed.util.Constants


/**
 * [RecyclerView.Adapter] that can display a [Item].
 */
class MyItemRecyclerViewAdapter(
    private val values: List<Item>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.setVariable(BR.item, item)

            binding.clickListener = object: AdClickedListener {
                override fun onAdClicked(item: Item) {
                    if(item.type == Constants.AD) {
                        item.url?.let {
                            val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
                            val customTabsIntent: CustomTabsIntent = builder.build()
                            customTabsIntent.launchUrl(binding.root.context, Uri.parse(it))
                        }
                    }
                }

            }
            binding.executePendingBindings()
        }
    }
}