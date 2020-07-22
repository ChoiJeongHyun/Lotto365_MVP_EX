package kr.co.example.lotto365_mvp.Views.p2_Main.Presenter

import kr.co.example.lotto365_mvp.Views.In_Common.Adapter.Contract.ListPageAdapterContract
import kr.co.example.lotto365_mvp.Views.p2_Main.Contract.MainContract

class MainPresenter : MainContract.Presenter {

    private lateinit var view: MainContract.View
    private lateinit var model: ListPageAdapterContract.Model
    private lateinit var adapterView: ListPageAdapterContract.View

    override fun setView(view: MainContract.View) {
        super.setView(view)
        this.view = view
    }

    override fun releaseView() {
        super.releaseView()
    }

    override fun setListAdapterModel(model: ListPageAdapterContract.Model) {
        this.model = model
    }

    override fun setListAdapterView(view: ListPageAdapterContract.View) {
        this.adapterView = view
    }

    override fun setItems() {
        val items = ArrayList<String>(arrayListOf("번호 생성", "내 번호", "당첨 확인(QR코드)"))
        model.addItems(items)
        adapterView.notifyAdapter()
    }

}