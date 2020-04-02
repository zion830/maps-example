package zion830.com.maps

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.naver.maps.map.overlay.Marker
import zion830.com.maps.model.SearchRepository
import zion830.com.maps.util.MapUtils
import zion830.com.maps.util.StringUtils

/*
 * Created by yunji on 02/04/2020
 */
class MainViewModel : ViewModel() {

    private val _previousMarkers = MutableLiveData<List<Marker>>()
    val previousMarkers: LiveData<List<Marker>> = _previousMarkers

    private val _markers = MutableLiveData<List<Marker>>()
    val markers: LiveData<List<Marker>> = _markers

    private val _category = MutableLiveData<String>("양식")
    val category: LiveData<String> = _category

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _bottomSheetVisibility = MutableLiveData<Boolean>(false)
    val bottomSheetVisibility: LiveData<Boolean> = _bottomSheetVisibility

    fun getMarkers() {
        val query = category.value ?: ""
        SearchRepository.getSearchResult(query, success = {
            it?.let {
                val newMarkers = it.items.map { item -> MapUtils.createMarker(item.mapx.toDouble(), item.mapy.toDouble()) }
                _markers.value = newMarkers

                if (previousMarkers.value.isNullOrEmpty()) {
                    _previousMarkers.value = newMarkers
                }
            }
        }, failed = { tag, msg ->
            Log.e(tag, msg)
            _message.value = StringUtils.getString(R.string.err_msg)
        })
    }

    fun updatePreviousMarker() {
        _previousMarkers.value = _markers.value
    }

    fun showBottomSheet() {
        _bottomSheetVisibility.value = true
    }

    fun hideBottomSheet() {
        _bottomSheetVisibility.value = false
    }
}