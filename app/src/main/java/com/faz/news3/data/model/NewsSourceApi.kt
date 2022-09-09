package com.faz.news3.data.model

import com.squareup.moshi.Json

data class NewsSourceApi(
    @field:Json(name = "id") val id: String? = null,
    @field:Json(name = "name") val name: String? = null
)