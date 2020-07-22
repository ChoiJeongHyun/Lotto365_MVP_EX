package kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kr.co.example.lotto365_mvp.R
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvp.Views.p3_GenerateNumber.Adapter.Contract.RandomORDreamAdapterContract
import kr.co.example.lotto365_mvp.Widgets.OnItemClickListener
import kr.co.example.lotto365_mvp.Widgets.RoundButton

class RandomORDreamNumberAdapter(val context: Context) :
    RecyclerView.Adapter<RandomORDreamNumberAdapter.RandomORDreamNumberItem>(),
    RandomORDreamAdapterContract.View, RandomORDreamAdapterContract.Model {

    private lateinit var items: ArrayList<ArrayList<String>>
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RandomORDreamNumberItem(parent)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RandomORDreamNumberItem, position: Int) {
        with(holder) {
            textNumber_one.text = items[position][0]
            textNumber_two.text = items[position][1]
            textNumber_three.text = items[position][2]
            textNumber_four.text = items[position][3]
            textNumber_five.text = items[position][4]
            textNumber_six.text = items[position][5]

            textNumber_one.setTextColor(Utils.getNumberColor(items[position][0].toInt()))
            textNumber_two.setTextColor(Utils.getNumberColor(items[position][1].toInt()))
            textNumber_three.setTextColor(Utils.getNumberColor(items[position][2].toInt()))
            textNumber_four.setTextColor(Utils.getNumberColor(items[position][3].toInt()))
            textNumber_five.setTextColor(Utils.getNumberColor(items[position][4].toInt()))
            textNumber_six.setTextColor(Utils.getNumberColor(items[position][5].toInt()))
        }

        holder.btnSaveAndRemove.setOnClickListener {
            onItemClickListener?.let {
                it.onItemClick(holder.itemView.parent as RecyclerView, holder.itemView, position)
            }
        }
    }

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }

    override fun addItems(items: ArrayList<ArrayList<String>>) {
        this.items = items
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }


    inner class RandomORDreamNumberItem(parent: ViewGroup) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_generate_number, parent, false)) {
        val btnSaveAndRemove: RoundButton = itemView.findViewById(R.id.item_view_btn_generate_number)
        val textNumber_one: TextView = itemView.findViewById(R.id.item_view_text_generate_number_number1)
        val textNumber_two: TextView = itemView.findViewById(R.id.item_view_text_generate_number_number2)
        val textNumber_three: TextView = itemView.findViewById(R.id.item_view_text_generate_number_number3)
        val textNumber_four: TextView = itemView.findViewById(R.id.item_view_text_generate_number_number4)
        val textNumber_five: TextView = itemView.findViewById(R.id.item_view_text_generate_number_number5)
        val textNumber_six: TextView = itemView.findViewById(R.id.item_view_text_generate_number_number6)
    }
}