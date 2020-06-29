package kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Contract

import android.content.Context
import kr.co.factoryx.lotto365_mvp.BaseContract
import kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Adapter.Contract.FixedAndExceptAdapterContract

interface RandomGenerateContract {

    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter<View> {
        override fun setView(view: View) {

        }

        override fun releaseView() {

        }

        override fun setDataSource(context: Context) {

        }

        fun setAdaptersModel(fixedModel: FixedAndExceptAdapterContract.Model , exceptModel: FixedAndExceptAdapterContract.Model)
        fun setAdaptersView(fixedView: FixedAndExceptAdapterContract.View, exceptView: FixedAndExceptAdapterContract.View)
        fun setItems()
    }

}