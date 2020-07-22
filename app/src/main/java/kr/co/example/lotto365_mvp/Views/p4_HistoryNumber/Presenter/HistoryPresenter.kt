package kr.co.example.lotto365_mvp.Views.p4_HistoryNumber.Presenter

import android.content.Context
import kr.co.example.lotto365_mvp.Model.LocalRandomDataSource
import kr.co.example.lotto365_mvp.Views.p4_HistoryNumber.Adapter.Contract.HistoryNumberAdapterContract
import kr.co.example.lotto365_mvp.Views.p4_HistoryNumber.Contract.HistoryContract

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