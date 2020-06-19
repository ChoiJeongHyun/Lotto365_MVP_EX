package kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.factoryx.lotto365_mvp.R
import kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Adapter.Contract.FixedAndExceptAdapterContract

class FixedAndExceptAdapter(val context: Context) : RecyclerView.Adapter<FixedAndExceptAdapter.FixedAndExceptItem>(),
FixedAndExceptAdapterContract.View, FixedAndExceptAdapterContract.Model{





    inner class FixedAndExceptItem(parent: ViewGroup) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_number, parent, false)) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixedAndExceptItem {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FixedAndExceptItem, position: Int) {
        TODO("Not yet implemented")
    }

    override fun notifyAdapter() {
        TODO("Not yet implemented")
    }

    override fun addItems(items: ArrayList<String>) {
        TODO("Not yet implemented")
    }

}