package kr.co.example.lotto365_mvp.Views.p1_Splash.Presenter

import android.content.Context
import com.google.firebase.iid.FirebaseInstanceId
import kr.co.example.lotto365_mvp.Common.commonset.C
import kr.co.example.lotto365_mvp.Utils.Utils
import kr.co.example.lotto365_mvp.Views.p1_Splash.Contract.SplashContract

class SplashPresenter : SplashContract.Presenter {

    private lateinit var view: SplashContract.View

    override fun setView(view: SplashContract.View) {
        this.view = view
    }

    override fun releaseView() {
        super.releaseView()

    }

    fun setFcmToken(context: Context) {
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
            if (!it.isSuccessful) {
                return@addOnCompleteListener
            }

            it.result?.let { result ->

                if (Utils.getPreferenceData(context, C.Pref.fcm_token, "") == "") {
                    Utils.setPreferenceData(context, C.Pref.fcm_token, result.token)
                } else {
                    if (Utils.getPreferenceData(context, C.Pref.fcm_token, "") != result.token) {
                        Utils.setPreferenceData(context, C.Pref.fcm_token, result.token)
                    }
                }

                Utils.postDelayed({
                    view.fcmTokenMappingOK()
                }, 500)


            }


        }
    }
}