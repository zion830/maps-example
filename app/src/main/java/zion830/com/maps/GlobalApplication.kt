package zion830.com.maps

import android.app.Application
import android.content.Context

/*
 * Created by yunji on 02/04/2020
 */
class GlobalApplication : Application() {

    companion object {
        private lateinit var APPLICATION_CONTEXT: Context

        @JvmStatic
        fun getContext(): Context {
            return APPLICATION_CONTEXT
        }
    }

    override fun onCreate() {
        super.onCreate()
        APPLICATION_CONTEXT = applicationContext
    }
}