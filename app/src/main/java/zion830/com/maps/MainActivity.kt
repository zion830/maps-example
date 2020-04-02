package zion830.com.maps

import android.os.Bundle
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import kotlinx.android.synthetic.main.activity_main.*
import zion830.com.maps.databinding.ActivityMainBinding
import zion830.com.maps.util.showToast


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initView()
    }

    private fun initView() {
        val fm = supportFragmentManager
        val mapFragment = map as MapFragment? ?: MapFragment.newInstance().also {
            fm.beginTransaction().add(R.id.map, it).commit()
        }

        binding.fab.setOnClickListener { viewModel.showBottomSheet() }
        mapFragment.getMapAsync(this)
    }

    private fun subscribeUi(naverMap: NaverMap) {
        with(viewModel) {
            category.observe(this@MainActivity, Observer { viewModel.getMarkers() })
            markers.observe(this@MainActivity, Observer { setMarkers(naverMap, it) })
            message.observe(this@MainActivity, Observer { showToast(it) })
        }
    }

    @UiThread
    override fun onMapReady(naverMap: NaverMap) {
        naverMap.addOnLocationChangeListener { location -> println("위치 변경 " + location.altitude + " " + location.latitude) }
        subscribeUi(naverMap)
        viewModel.getMarkers()
    }

    private fun setMarkers(naverMap: NaverMap, markers: List<Marker>) {
        clearMarker()
        for (marker in markers) {
            marker.map = naverMap
        }
    }

    private fun clearMarker() {
        val previousMarker: List<Marker> = viewModel.previousMarkers.value ?: listOf()
        for (marker in previousMarker) {
            marker.map = null
        }

        viewModel.updatePreviousMarker()
    }
}
