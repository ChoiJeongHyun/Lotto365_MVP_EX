package kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Popup.PopupAdapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kr.co.factoryx.lotto365.vo.Number
import kr.co.factoryx.lotto365_mvp.R
import kr.co.factoryx.lotto365_mvp.Utils.Utils
import kr.co.factoryx.lotto365_mvp.Widgets.CircleImageView
import kr.co.factoryx.lotto365_mvp.Widgets.OnItemClickListener

class NumberPopupAdapter(val context: Context, var items: ArrayList<Number>, var fixedItems: ArrayList<String>, var exceptItems: ArrayList<String>, var isFixed: Boolean) :
    RecyclerView.Adapter<NumberPopupAdapter.NumberItem>() {

    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NumberItem(parent)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: NumberItem, position: Int) {
        items[position].let {
            with(holder) {
                if (isFixed) {
                    textNumber.text = if (exceptItems.contains(it.position)) "" else it.position
                    textNumber.setTextColor(if (fixedItems.contains(it.position)) Color.parseColor("#191F28") else Color.parseColor("#8B95A1"))
                    backgroundImage.setImageDrawable(if (fixedItems.contains(it.position)) ColorDrawable(Utils.getNumberColor(position + 1)) else ColorDrawable(Color.parseColor("#338B95A1")))
                } else {
                    textNumber.text = if (fixedItems.contains(it.position)) "" else it.position
                    textNumber.setTextColor(if (exceptItems.contains(it.position)) Color.parseColor("#191F28") else Color.parseColor("#8B95A1"))
                    backgroundImage.setImageDrawable(if (exceptItems.contains(it.position)) ColorDrawable(Utils.getNumberColor(position + 1)) else ColorDrawable(Color.parseColor("#338B95A1")))
                }

            }
        }



        holder.itemView.setOnClickListener {
            if (isFixed) {
                if (items[position].isEXCEPT) {
                    Toast.makeText(context, "제외번호입니다.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (fixedItems.size == 5) {
                    if (!items[position].isFixed) {
                        Toast.makeText(context, "최대 5개까지 선택가능합니다.", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                }

                if (items[position].isFixed) fixedItems.remove(items[position].position) else fixedItems.add(items[position].position)
                items[position].isFixed = !items[position].isFixed

                holder.backgroundImage.setImageDrawable(if (fixedItems.contains(items[position].position)) ColorDrawable(Utils.getNumberColor(position + 1)) else ColorDrawable(Color.parseColor("#338B95A1")))
                holder.textNumber.setTextColor(if (fixedItems.contains(items[position].position)) Color.parseColor("#191F28") else Color.parseColor("#8B95A1"))

            } else {
                if (items[position].isFixed) {
                    Toast.makeText(context, "고정번호입니다.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (exceptItems.size == 35) {
                    if (!items[position].isEXCEPT) {
                        Toast.makeText(context, "최대 35개까지 선택가능합니다.", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                }


                if (items[position].isEXCEPT) exceptItems.remove(items[position].position) else exceptItems.add(items[position].position)
                items[position].isEXCEPT = !items[position].isEXCEPT

                holder.backgroundImage.setImageDrawable(if (exceptItems.contains(items[position].position)) ColorDrawable(Utils.getNumberColor(position + 1)) else ColorDrawable(Color.parseColor("#338B95A1")))
                holder.textNumber.setTextColor(if (exceptItems.contains(items[position].position)) Color.parseColor("#191F28") else Color.parseColor("#8B95A1"))
            }

            onItemClickListener?.let {
                it.onItemClick(holder.itemView.parent as RecyclerView, holder.itemView, position)
            }
        }

    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    inner class NumberItem(parent: ViewGroup) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_popup_number, parent, false)) {
        val backgroundImage: CircleImageView = itemView.findViewById(R.id.item_view_circle_popup_number)
        val textNumber: TextView = itemView.findViewById(R.id.item_view_text_number_popup_number)
    }


}