package kr.co.factoryx.lotto365_mvp.Views.p4_HistoryNumber.Adapter.Contract

import kr.co.factoryx.lotto365_mvp.BaseAdapterContract
import kr.co.factoryx.lotto365_mvp.MiniFrameWork.DataBase.Entity.History

interface HistoryNumberAdapterContract {

    interface View : BaseAdapterContract.View {
        fun notifyAdapter()
    }

    interface Model : BaseAdapterContract.Model {
        fun addItems(items: ArrayList<History>)

        fun removeItem(position: Int)

        fun removeALL()
    }

}