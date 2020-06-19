package kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Presenter

import android.content.Context
import kr.co.factoryx.lotto365_mvp.Views.In_Common.Adapter.Contract.ListPageAdapterContract
import kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Contract.GenerateContract

class GeneratePresenter : GenerateContract.Presenter {

    private lateinit var view: GenerateContract.View
    private lateinit var model: ListPageAdapterContract.Model
    private lateinit var adapterView: ListPageAdapterContract.View


    override fun setView(view: GenerateContract.View) {
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
        val items = ArrayList<String>(arrayListOf("꿈 분석", "랜덤 생성"))
        model.addItems(items)
        adapterView.notifyAdapter()
    }


}