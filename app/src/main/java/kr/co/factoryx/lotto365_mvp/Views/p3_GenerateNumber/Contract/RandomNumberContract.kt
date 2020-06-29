package kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Contract

import android.content.Context
import kr.co.factoryx.lotto365_mvp.BaseContract
import kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Adapter.Contract.RandomORDreamAdapterContract

interface RandomNumberContract {

    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter<View> {
        override fun setView(view: View) {

        }

        override fun setDataSource(context: Context) {

        }

        override fun releaseView() {

        }

        fun setAdapterModel(model : RandomORDreamAdapterContract.Model)
        fun setAdapterView(view : RandomORDreamAdapterContract.View)
        fun setItems()
    }

}