package kr.co.factoryx.lotto365_mvp.Views.p1_Splash.Activity

import android.os.Bundle
import kr.co.factoryx.lotto365.commonset.components.LottoActivity
import kr.co.factoryx.lotto365_mvp.R
import kr.co.factoryx.lotto365_mvp.Utils.PLog
import kr.co.factoryx.lotto365_mvp.Views.p1_Splash.Contract.SplashContract
import kr.co.factoryx.lotto365_mvp.Views.p1_Splash.Presenter.SplashPresenter
import kr.co.factoryx.lotto365_mvp.Views.p2_Main.Activity.MainActivity

class SplashActivity : LottoActivity(), SplashContract.View {

    private lateinit var presenter: SplashPresenter

    override fun initPresenter() {
        presenter = SplashPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        viewInit()
        setData()

        presenter.setView(this)
        presenter.setFcmToken(this)

    }

    override fun viewInit() {
        super.viewInit()
    }

    override fun setData() {
        super.setData()
    }

    override fun fcmTokenMappingOK() {
        sendAction(MainActivity::class.java)
        finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.releaseView()
    }


}