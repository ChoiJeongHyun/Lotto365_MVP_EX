package kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Activity.Random

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_random_generate.*
import kr.co.factoryx.lotto365.commonset.components.LottoActivity
import kr.co.factoryx.lotto365_mvp.Common.commonset.C
import kr.co.factoryx.lotto365_mvp.R
import kr.co.factoryx.lotto365_mvp.Utils.Utils
import kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Contract.RandomGenerateContract
import kr.co.factoryx.lotto365_mvp.Views.p3_GenerateNumber.Presenter.RandomGeneratePresenter
import kr.co.factoryx.lotto365_mvp.Widgets.ToolBar

class RandomGenerateActivity : RandomGenerateContract.View, LottoActivity(),
    ToolBar.OnToolBarClickListener, View.OnClickListener {

    private lateinit var presenter: RandomGeneratePresenter

    override fun initPresenter() {
        presenter = RandomGeneratePresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_generate)
        Utils.loadAdView(view_random_generate_adView)

        presenter.setView(this)

        viewInit()
        setData()
    }

    override fun viewInit() {
        super.viewInit()
        view_random_generate_toolbar.setLeftButton(ToolBar.ButtonItem().setTag(C.Tag.TOOLBAR_BACK))
        view_random_generate_toolbar.setOnToolBarClickListener(this)

        view_random_generate_group_fixed_number.setOnClickListener(this)
        view_random_generate_group_except_number.setOnClickListener(this)
        view_random_generate_btn_generate_number.setOnClickListener(this)

        view_random_generate_group_fixed_number.setAlWaysMyTouch(true)
        view_random_generate_group_except_number.setAlWaysMyTouch(true)

    }

    override fun setData() {
        super.setData()
    }

    override fun onClick(v: View?) {
        super.onClick(v)
    }

    override fun onToolBarClick(tag: Any?, view: View?) {
        if (Utils.equals(tag, C.Tag.TOOLBAR_BACK)) {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.releaseView()
    }

}