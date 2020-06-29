package kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Popup

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.factoryx.lotto365.commonset.components.abspopupview.AbsFadeInView
import kr.co.factoryx.lotto365.vo.Number
import kr.co.factoryx.lotto365_mvp.Common.commonset.C
import kr.co.factoryx.lotto365_mvp.R
import kr.co.factoryx.lotto365_mvp.Utils.PLog
import kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Popup.PopupAdapter.NumberPopupAdapter
import kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Presenter.RandomGeneratePresenter
import kr.co.factoryx.lotto365_mvp.Widgets.OnPopupClickListener
import java.util.*
import kotlin.collections.ArrayList

class NumberPopup(context: Context) : AbsFadeInView(context), View.OnClickListener {

    private lateinit var viewMapper: ViewMapper
    private lateinit var fixedArrayCopy: ArrayList<String>
    private lateinit var exceptArrayCopy: ArrayList<String>
    private var numberItems = ArrayList<Number>()
    private var adapter: NumberPopupAdapter? = null


    private lateinit var presenter: RandomGeneratePresenter

    private var title: String = ""
    private var isFixed = true


    private var onPopupClickListener: OnPopupClickListener? = null

    override fun contentView(): View = View.inflate(getContext(), R.layout.popup_number, null)

    override fun onCreateView() {
        viewMapper = ViewMapper()
        viewMapper.groupPopup.setOnClickListener(this)
        viewMapper.btnOK.setOnClickListener(this)
        viewMapper.btnCancel.setOnClickListener(this)
        viewMapper.btnClear.setOnClickListener(this)

        PLog.e("onCre??View")
        settingData()
    }

    private fun settingData() {
        fixedArrayCopy = ArrayList(presenter.getFixedNumber())
        exceptArrayCopy = ArrayList(presenter.getExceptNumber())
        for (i in 0..44) {

            var n = Number()
            n.position = (i + 1).toString()

            when {
                fixedArrayCopy.contains(n.position) -> {
                    n.isFixed = true
                    n.isEXCEPT = false
                }
                exceptArrayCopy.contains(n.position) -> {
                    n.isFixed = false
                    n.isEXCEPT = true
                }
                else -> {
                    n.isFixed = false
                    n.isEXCEPT = false
                }
            }

            numberItems.add(n)
        }

        PLog.e("popup getData : $fixedArrayCopy")

    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v) {
            viewMapper.btnCancel -> getActivity().onBackPressed()
            viewMapper.btnOK -> {
                fixedArrayCopy.sortWith(Comparator { o1, o2 -> o1.toInt().compareTo(o2.toInt()) })
                exceptArrayCopy.sortWith(Comparator { o1, o2 -> o1.toInt().compareTo(o2.toInt()) })

                presenter.saveFixedNumber(fixedArrayCopy)
                presenter.saveExceptNumber(exceptArrayCopy)

                PLog.e("popup onClick $fixedArrayCopy")

                onPopupClickListener?.let {
                    it.onPopupClick(this, C.Popup.RIGHT, null)
                }
                getActivity().onBackPressed()
            }
            viewMapper.btnClear -> {
                if (isFixed) {
                    for (i in 0 until fixedArrayCopy.size) {
                        numberItems[fixedArrayCopy[i].toInt() - 1].isFixed = false
                    }
                    fixedArrayCopy.clear()
                } else {
                    for (i in 0 until exceptArrayCopy.size) {
                        numberItems[exceptArrayCopy[i].toInt() - 1].isEXCEPT = false
                    }
                    exceptArrayCopy.clear()
                }
                viewMapper.listView.adapter?.notifyDataSetChanged()
            }
        }
    }

    fun setTitle(s: String?): NumberPopup {
        s?.apply {
            title = this
        }
        return this@NumberPopup
    }

    fun setFixed(isFixed: Boolean): NumberPopup {
        this.isFixed = isFixed
        return this
    }

    fun setPresenter(presenter: RandomGeneratePresenter): NumberPopup {
        this.presenter = presenter
        return this
    }

    fun setOnPopupClickListener(onPopupClickListener: OnPopupClickListener): NumberPopup {
        this.onPopupClickListener = onPopupClickListener
        return this
    }

    override fun show() {
        super.show()
        viewMapper.textTitle.text = title
        viewMapper.listView.layoutManager = GridLayoutManager(getContext(), 5)
        viewMapper.listView.adapter = NumberPopupAdapter(getContext(), numberItems, fixedArrayCopy, exceptArrayCopy, isFixed)
    }

    inner class ViewMapper {
        var groupPopup = findViewById<ViewGroup>(R.id.popup_number_group)
        var textTitle = findViewById<TextView>(R.id.popup_number_view_text_title)
        var btnClear = findViewById<View>(R.id.popup_number_view_btn_all_clear)
        var btnOK = findViewById<View>(R.id.popup_number_view_btn_ok)
        var btnCancel = findViewById<View>(R.id.popup_number_view_btn_cancel)
        var listView = findViewById<RecyclerView>(R.id.popup_number_listView)
    }
}