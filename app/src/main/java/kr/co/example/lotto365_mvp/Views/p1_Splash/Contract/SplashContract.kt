package kr.co.example.lotto365_mvp.Views.p1_Splash.Contract

import android.content.Context
import kr.co.example.lotto365_mvp.BaseContract

interface SplashContract {

    interface View : BaseContract.View {
        fun fcmTokenMappingOK()
    }

    interface Presenter : BaseContract.Presenter<View> {
        override fun setView(view: View) {
        }

        override fun releaseView() {

        }

        override fun setDataSource(context: Context) {

        }
    }
}