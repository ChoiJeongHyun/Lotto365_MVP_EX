package kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Contract

import android.content.Context
import kr.co.factoryx.lotto365_mvp.BaseContract
import kr.co.factoryx.lotto365_mvp.Views.In_Common.Adapter.Contract.ListPageAdapterContract

interface GenerateContract {

    interface View: BaseContract.View{

    }

    interface Presenter: BaseContract.Presenter<View>{
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