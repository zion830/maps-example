package zion830.com.maps.network

import okhttp3.Interceptor
import okhttp3.Response
import zion830.com.maps.HEADER_CLIENT_ID
import zion830.com.maps.HEADER_CLIENT_SECRET
import zion830.com.maps.R
import zion830.com.maps.util.StringUtils
import java.io.IOException

/*
 * Created by yunji on 02/04/2020
 */
class HeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val origin = request()
        val request = origin.newBuilder()
            .addHeader(HEADER_CLIENT_ID, StringUtils.getString(R.string.search_client_id))
            .addHeader(HEADER_CLIENT_SECRET, StringUtils.getString(R.string.search_client_secret))
            .build()
        proceed(request)
    }
}