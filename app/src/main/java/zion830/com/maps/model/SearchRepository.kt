package zion830.com.maps.model

import zion830.com.maps.network.RetrofitBuilder
import zion830.com.maps.network.ServiceApi
import zion830.com.maps.network.callback

/*
 * Created by yunji on 02/04/2020
 */
object SearchRepository {
    private val TAG: String = SearchRepository::class.java.name
    private val serviceApi: ServiceApi = RetrofitBuilder.service

    fun getSearchResult(
        query: String,
        success: (response: SearchResponse?) -> Unit,
        failed: (String, String) -> Unit
    ) {
        serviceApi.getSearchResult(query).enqueue(callback { response, throwable ->
            response?.let {
                if (it.isSuccessful) {
                    success(it.body())
                } else {
                    failed(TAG, it.message() ?: "getSearchResult")
                }
            }
            throwable?.let {
                failed(TAG, it.message ?: "getSearchResult")
            }
        })
    }
}