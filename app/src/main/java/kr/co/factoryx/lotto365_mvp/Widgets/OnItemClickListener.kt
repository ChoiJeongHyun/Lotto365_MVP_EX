package kr.co.factoryx.lotto365_mvp.Widgets

import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface OnItemClickListener {
    fun onItemClick(recyclerView: RecyclerView, view: View, position: Int)
}