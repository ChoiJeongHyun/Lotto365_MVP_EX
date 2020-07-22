package kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kr.co.example.lotto365_mvp.R
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Adapter.Contract.FixedAndExceptAdapterContract
import kr.co.example.lotto365_mvp.Widgets.CircleImageView
import kr.co.example.lotto365_mvp.Widgets.OnItemClickListener

class FixedAndExceptAdapter(val context: Context) :
    RecyclerView.Adapter<FixedAndExceptAdapter.FixedAndExceptItem>(),
    FixedAndExceptAdapterContract.View, FixedAndExceptAdapterContract.Model {

    private lateinit var items: ArrayList<String>
    private var onItemClickListener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FixedAndExceptItem(parent)
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: FixedAndExceptItem, position: Int) {
        with(holder) {
            textNumber.text = items[position]
            textNumber.setTextColor(Utils.getNumberColor(items[position].toInt()))
            backgroundImage.setImageDrawable(null)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it.onItemClick(holder.itemView.parent as RecyclerView, holder.itemView, position)
            }
        }
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun addItems(items: ArrayList<String>) {
        this.items = items
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    inner class FixedAndExceptItem(parent: ViewGroup) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_number, parent, false)) {
        val backgroundImage: CircleImageView = itemView.findViewById(R.id.item_view_circle_number)
        val textNumber: TextView = itemView.findViewById(R.id.item_view_text_number)
    }

}