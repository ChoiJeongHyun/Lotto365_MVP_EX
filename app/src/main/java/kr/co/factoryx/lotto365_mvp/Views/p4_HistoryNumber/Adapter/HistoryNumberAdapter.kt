package kr.co.factoryx.lotto365_mvp.Views.p4_HistoryNumber.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kr.co.factoryx.lotto365_mvp.MiniFrameWork.DataBase.Entity.History
import kr.co.factoryx.lotto365_mvp.R
import kr.co.factoryx.lotto365_mvp.Utils.Utils
import kr.co.factoryx.lotto365_mvp.Views.p4_HistoryNumber.Adapter.Contract.HistoryNumberAdapterContract
import kr.co.factoryx.lotto365_mvp.Widgets.OnItemClickListener
import kr.co.factoryx.lotto365_mvp.Widgets.RoundButton
import java.lang.Exception

class HistoryNumberAdapter(val context: Context) :
    RecyclerView.Adapter<HistoryNumberAdapter.HistoryItem>(), HistoryNumberAdapterContract.View,
    HistoryNumberAdapterContract.Model {

    private var onItemClickListener: OnItemClickListener? = null
    private lateinit var items: ArrayList<History>


    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun addItems(items: ArrayList<History>) {
        this.items = items
    }

    override fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    override fun removeALL() {
        items.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItem = HistoryItem(parent)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: HistoryItem, position: Int) {
        with(holder) {
            textNumber_one.text = items[position].numbers[0]
            textNumber_two.text = items[position].numbers[1]
            textNumber_three.text = items[position].numbers[2]
            textNumber_four.text = items[position].numbers[3]
            textNumber_five.text = items[position].numbers[4]
            textNumber_six.text = items[position].numbers[5]

            textNumber_one.setTextColor(Utils.getNumberColor(items[position].numbers[0]!!.toInt()))
            textNumber_two.setTextColor(Utils.getNumberColor(items[position].numbers[1]!!.toInt()))
            textNumber_three.setTextColor(Utils.getNumberColor(items[position].numbers[2]!!.toInt()))
            textNumber_four.setTextColor(Utils.getNumberColor(items[position].numbers[3]!!.toInt()))
            textNumber_five.setTextColor(Utils.getNumberColor(items[position].numbers[4]!!.toInt()))
            textNumber_six.setTextColor(Utils.getNumberColor(items[position].numbers[5]!!.toInt()))

            textType.text = if (items[position].type == "Random") "랜덤생성" else "꿈생성"

            try {
                if (position == 0) {
                    textDate.visibility = View.VISIBLE
                    textDate.text = Utils.dataFormat(items[position].historyDate, "yyyy-MM-dd")
                } else {
                    if (Utils.dateCompare(items[position - 1].historyDate.toString(), items[position].historyDate.toString()) > 0) {
                        textDate.visibility = View.VISIBLE
                        textDate.text = Utils.dataFormat(items[position].historyDate, "yyyy-MM-dd")
                    } else {
                        textDate.visibility = View.GONE
                    }
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

        holder.btnClear.setOnClickListener {
            onItemClickListener?.let {
                it.onItemClick(holder.itemView.parent as RecyclerView, holder.itemView, position)
            }
        }

    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }


    inner class HistoryItem(parent: ViewGroup) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_history_number, parent, false)) {

        val btnClear: RoundButton = itemView.findViewById(R.id.item_view_btn_history_number)
        val textNumber_one: TextView = itemView.findViewById(R.id.item_view_text_history_number_number1)
        val textNumber_two: TextView = itemView.findViewById(R.id.item_view_text_history_number_number2)
        val textNumber_three: TextView = itemView.findViewById(R.id.item_view_text_history_number_number3)
        val textNumber_four: TextView = itemView.findViewById(R.id.item_view_text_history_number_number4)
        val textNumber_five: TextView = itemView.findViewById(R.id.item_view_text_history_number_number5)
        val textNumber_six: TextView = itemView.findViewById(R.id.item_view_text_history_number_number6)
        val textDate: TextView = itemView.findViewById(R.id.item_view_text_history_number_date)
        val textType: TextView = itemView.findViewById(R.id.item_view_text_history_number_type)

    }
}