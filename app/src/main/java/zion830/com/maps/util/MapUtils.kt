package zion830.com.maps.util

import com.naver.maps.geometry.Tm128
import com.naver.maps.map.overlay.Marker

/*
 * Created by yunji on 02/04/2020
 */
object MapUtils {

    fun createMarker(mapX: Double, mapY: Double) = Marker().apply {
        position = Tm128(mapX, mapY).toLatLng()
    }
}