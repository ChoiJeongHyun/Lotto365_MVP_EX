package kr.co.factoryx.lotto365_mvp.MiniFrameWork.DataBase.Entity

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class History : RealmObject() {
    @PrimaryKey
    var uniqueId: String = ""
    var historyDate: Long = System.currentTimeMillis()
    var numbers: RealmList<String> = RealmList()
    var type: String = "Random"
}