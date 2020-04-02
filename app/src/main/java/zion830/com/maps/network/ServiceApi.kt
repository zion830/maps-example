package zion830.com.maps.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import zion830.com.maps.DISPLAY_COUNT
import zion830.com.maps.model.SearchResponse

/*
 * Created by yunji on 02/04/2020
 */
interface ServiceApi {

    @GET("search/local")
    fun getSearchResult(
        @Query("query") query: String,
        @Query("start") start: Int = 1,
        @Query("display") display: Int = DISPLAY_COUNT
    ): Call<SearchResponse>

    @GET("map/reversegeocode")
    fun getLocationName(
        @Query("coords") location: String
    )
}