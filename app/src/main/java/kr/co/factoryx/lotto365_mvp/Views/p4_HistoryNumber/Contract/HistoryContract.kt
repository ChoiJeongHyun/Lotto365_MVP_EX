package kr.co.factoryx.lotto365_mvp.Views.p4_HistoryNumber.Contract

import android.content.Context
import kr.co.factoryx.lotto365_mvp.BaseContract
import kr.co.factoryx.lotto365_mvp.MiniFrameWork.DataBase.Entity.History
import kr.co.factoryx.lotto365_mvp.Views.In_Common.Adapter.Contract.ListPageAdapterContract
import kr.co.factoryx.lotto365_mvp.Views.p4_HistoryNumber.Adapter.Contract.HistoryNumberAdapterContract

interface HistoryContract {

    interface View : BaseContract.View {
        fun showingMsg(arrays : ArrayList<History>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        override fun setView(view: View) {

        }

        override fun releaseView() {

        }

        override fun setDataSource(context: Context) {

        }

        fun setListAdapterModel(model: HistoryNumberAdapterContract.Model)

        fun setListAdapterView(view: HistoryNumberAdapterContract.View)

        fun setItems()

    }
}