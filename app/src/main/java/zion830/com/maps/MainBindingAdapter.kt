package zion830.com.maps

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior

/*
 * Created by yunji on 02/04/2020
 */
@BindingAdapter("sheet_visibility")
fun setVisibility(layout: ConstraintLayout, visibility: Boolean) {
    val bottomSheetBehavior = BottomSheetBehavior.from(layout)
    bottomSheetBehavior.state =
        if (visibility) BottomSheetBehavior.STATE_HALF_EXPANDED else BottomSheetBehavior.STATE_HIDDEN
}