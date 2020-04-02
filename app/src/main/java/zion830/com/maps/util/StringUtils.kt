package zion830.com.maps.util

import zion830.com.maps.GlobalApplication

/*
 * Created by yunji on 02/04/2020
 */
object StringUtils {
    fun getString(resId: Int) = GlobalApplication.getContext().getString(resId)
}