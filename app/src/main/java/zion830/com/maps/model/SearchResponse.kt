package zion830.com.maps.model


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val items: List<SearchItem>,
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("start")
    val start: Int,
    @SerializedName("total")
    val total: Int
)