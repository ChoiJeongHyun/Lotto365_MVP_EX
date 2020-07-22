package kr.co.example.lotto365_mvp.Views.p2_Main.Contract

import android.content.Context
import kr.co.example.lotto365_mvp.BaseContract
import kr.co.example.lotto365_mvp.Views.In_Common.Adapter.Contract.ListPageAdapterContract

interface MainContract {

    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter<View> {
        override fun setView(view: View) {

        }

        override fun releaseView() {

        }

        override fun setDataSource(context: Context) {

        }

        fun setListAdapterModel(model: ListPageAdapterContract.Model)

        fun setListAdapterView(view: ListPageAdapterContract.View)

        fun setItems()

    }
}