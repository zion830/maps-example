package zion830.com.maps.util

import android.widget.Toast
import zion830.com.maps.GlobalApplication

/*
 * Created by yunji on 09/03/2020
 */
fun showToast(text: String) {
    Toast.makeText(GlobalApplication.getContext(), text, Toast.LENGTH_SHORT).show()
}

fun showToast(resId: Int) {
    val context = GlobalApplication.getContext()
    Toast.makeText(context, context.getString(resId), Toast.LENGTH_SHORT).show()
}