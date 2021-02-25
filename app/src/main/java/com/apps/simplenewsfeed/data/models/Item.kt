package com.apps.simplenewsfeed.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apps.simplenewsfeed.util.ItemStyle
import com.apps.simplenewsfeed.util.ItemType
import com.google.gson.annotations.SerializedName

@Entity(tableName = "items")
data class Item(
    @SerializedName("id")
    @PrimaryKey
    val id: String,

    @SerializedName("lastModified")
    val lastModified: String? = null,

    @SerializedName("style")
    @ItemStyle
    val style: String? = null,

    @SerializedName("subtitle")
    val subtitle: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("type")
    @ItemType
    val type: String,

    @SerializedName("url")
    val url: String? = null
)