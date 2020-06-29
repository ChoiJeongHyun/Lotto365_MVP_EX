package kr.co.factoryx.lotto365_mvp.Views.p4_HistoryNumber.Presenter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kr.co.factoryx.lotto365_mvp.BaseAdapterContract
import kr.co.factoryx.lotto365_mvp.Common.commonset.C
import kr.co.factoryx.lotto365_mvp.Model.LocalRandomDataSource
import kr.co.factoryx.lotto365_mvp.Utils.Utils
import kr.co.factoryx.lotto365_mvp.Views.In_Common.Adapter.Contract.ListPageAdapterContract
import kr.co.factoryx.lotto365_mvp.Views.p2_Main.Contract.MainContract
import kr.co.factoryx.lotto365_mvp.Views.p4_HistoryNumber.Adapter.Contract.HistoryNumberAdapterContract
import kr.co.factoryx.lotto365_mvp.Views.p4_HistoryNumber.Contract.HistoryContract
import kr.co.factoryx.lotto365_mvp.Widgets.OnItemClickListener
import kr.co.factoryx.lotto365_mvp.Widgets.ToolBar

class HistoryPresenter : HistoryContract.Presenter {

    private lateinit var view: HistoryContract.View
    private lateinit var dataSource: LocalRandomDataSource
    private lateinit var model: HistoryNumberAdapterContract.Model
    private lateinit var adapterView: HistoryNumberAdapterContract.View

    override fun setView(view: HistoryContract.View) {
        super.setView(view)
        this.view = view
    }

    override fun setDataSource(context: Context) {
        super.setDataSource(context)
        this.dataSource = LocalRandomDataSource(context, this)
    }

    override fun releaseView() {
        super.releaseView()
    }

    override fun setListAdapterModel(model: HistoryNumberAdapterContract.Model) {
        this.model = model
    }

    override fun setListAdapterView(view: HistoryNumberAdapterContract.View) {
        this.adapterView = view
    }

    override fun setItems() {
        model.addItems(dataSource.getAllHistory())
        adapterView.notifyAdapter()

        view.showingMsg(dataSource.getAllHistory())
    }

    fun removeItem(position: Int) {
        dataSource.removeHistory(dataSource.getAllHistory()[position].uniqueId)
        model.removeItem(position)
    }

    fun removeAll() {
        dataSource.removeAllHistory()
        model.removeALL()
        adapterView.notifyAdapter()

    }

    fun dbQuerySuccessing() {
        view.showingMsg(dataSource.getAllHistory())
    }

}