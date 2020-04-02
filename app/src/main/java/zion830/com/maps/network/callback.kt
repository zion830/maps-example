package zion830.com.maps.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
 * Created by yunji on 02/04/2020
 */
fun <T> callback(
    fn: (Response<T>?, Throwable?) -> Unit
): Callback<T> {
    return object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) = fn(response, null)
        override fun onFailure(call: Call<T>, t: Throwable) = fn(null, t)
    }
}