package kr.co.factoryx.lotto365_mvp.Views.p2_Main.Presenter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kr.co.factoryx.lotto365_mvp.BaseAdapterContract
import kr.co.factoryx.lotto365_mvp.Common.commonset.C
import kr.co.factoryx.lotto365_mvp.Utils.Utils
import kr.co.factoryx.lotto365_mvp.Views.In_Common.Adapter.Contract.ListPageAdapterContract
import kr.co.factoryx.lotto365_mvp.Views.p2_Main.Contract.MainContract
import kr.co.factoryx.lotto365_mvp.Widgets.OnItemClickListener
import kr.co.factoryx.lotto365_mvp.Widgets.ToolBar

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