package com.apps.simplenewsfeed.util

import com.apps.simplenewsfeed.data.models.Item

interface AdClickedListener {
    fun onAdClicked(item: Item)
}