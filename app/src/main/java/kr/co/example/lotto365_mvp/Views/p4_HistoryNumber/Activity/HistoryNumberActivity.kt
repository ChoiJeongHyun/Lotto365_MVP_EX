package kr.co.example.lotto365_mvp.Views.p4_HistoryNumber.Activity

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_history_number.*
import kr.co.example.lotto365.commonset.components.LottoActivity
import kr.co.example.lotto365_mvp.Common.commonset.C
import kr.co.example.lotto365_mvp.MiniFrameWork.DataBase.Entity.History
import kr.co.example.lotto365_mvp.R
import kr.co.example.lotto365_mvp.Utils.PLog
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvp.Views.p4_HistoryNumber.Adapter.HistoryNumberAdapter
import kr.co.example.lotto365_mvp.Views.p4_HistoryNumber.Contract.HistoryContract
import kr.co.example.lotto365_mvp.Views.p4_HistoryNumber.Presenter.HistoryPresenter
import kr.co.example.lotto365_mvp.Widgets.OnItemClickListener
import kr.co.example.lotto365_mvp.Widgets.ToolBar

class HistoryNumberActivity : LottoActivity(), ToolBar.OnToolBarClickListener, OnItemClickListener,
    HistoryContract.View {

    private lateinit var presenter: HistoryPresenter
    private lateinit var adapter: HistoryNumberAdapter

    override fun initPresenter() {
        presenter = HistoryPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_number)
        Utils.loadAdView(view_history_number_adView)

        presenter.setView(this)
        presenter.setDataSource(this)

        viewInit()
        setData()
    }

    override fun viewInit() {
        super.viewInit()
        view_history_number_toolbar.setLeftButton(ToolBar.ButtonItem().setTag(C.Tag.TOOLBAR_BACK))
        view_history_number_toolbar.setOnToolBarClickListener(this)
        view_history_number_btn_clear_number.setOnClickListener(this)

        view_history_number_listView.layoutManager = LinearLayoutManager(this)
        adapter = HistoryNumberAdapter(this)

        presenter.setListAdapterModel(adapter)
        presenter.setListAdapterView(adapter)

        view_history_number_listView.adapter = this.adapter
        adapter.setOnItemClickListener(this)

    }

    override fun setData() {
        super.setData()
        presenter.setItems()
    }


    override fun onClick(v: View?) {
        super.onClick(v)
        when (v) {
            view_history_number_btn_clear_number -> {
                presenter.removeAll()
            }
        }
    }

    override fun onItemClick(recyclerView: RecyclerView, view: View, position: Int) {
        presenter.removeItem(position)
    }

    override fun showingMsg(arrays: ArrayList<History>) {
        PLog.e("showingMsg size ${arrays.size}")
        view_history_number_text.visibility = if (arrays.size == 0) View.VISIBLE else View.GONE
    }

    override fun onToolBarClick(tag: Any?, view: View?) {
        if (Utils.equals(tag, C.Tag.TOOLBAR_BACK)) {
            super.onBackPressed()
        }
    }


}