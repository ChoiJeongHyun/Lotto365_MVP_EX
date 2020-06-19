package kr.co.factoryx.lotto365_mvp.Views.In_Common.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kr.co.factoryx.lotto365_mvp.R
import kr.co.factoryx.lotto365_mvp.Utils.PLog
import kr.co.factoryx.lotto365_mvp.Views.In_Common.Adapter.Contract.ListPageAdapterContract
import kr.co.factoryx.lotto365_mvp.Widgets.OnItemClickListener

class ListPageAdapter(val context: Context) : RecyclerView.Adapter<ListPageAdapter.ListPageItem>(),
    ListPageAdapterContract.View, ListPageAdapterContract.Model {

    private lateinit var items: ArrayList<String>
    private var onItemClickListener: OnItemClickListener? = null

    override fun addItems(items: ArrayList<String>) {
        this.items = items
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListPageItem(parent)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ListPageItem, position: Int) {
        items[position].let {
            holder.textTitle.text = it
        }

        holder.textTitle.setOnClickListener {
            onItemClickListener?.let {
                it.onItemClick(holder.itemView.parent as RecyclerView, holder.textTitle, position)
            }
        }

    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }


    inner class ListPageItem(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_page, parent, false)
    ) {
        val textTitle: TextView = itemView.findViewById(R.id.item_view_btn_list_page)
    }


}