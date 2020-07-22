package kr.co.example.lotto365_mvp.Store

import io.realm.Sort
import kr.co.example.lotto365.miniframework.database.dbmanager.LTDBManager
import kr.co.example.lotto365_mvp.MiniFrameWork.DataBase.Entity.History


object HistoryStore {

    fun insert(history: History, listener: queryListener?) {
        if (select(history.uniqueId) != null) {
            listener?.let {
                listener.queryResult(success = true, exist = true)
            }
            return
        }

        LTDBManager.getInstance().getRealm().executeTransactionAsync({
            it.insertOrUpdate(history)
        }, {
            listener?.let {
                listener.queryResult(success = true, exist = false)
            }
            LTDBManager.getInstance().getRealm().close()

        }, {
            listener?.let {
                listener.queryResult(success = false, exist = false)
            }
            LTDBManager.getInstance().getRealm().close()
        })
    }

    fun insertAll(historys: ArrayList<History>, listener: queryListener?) {
        for (i in 0 until historys.size) {
            if (select(historys[i].uniqueId) != null) {
                listener?.let {
                    listener.queryResult(success = true, exist = true)
                }
                return
            }
        }

        LTDBManager.getInstance().getRealm().executeTransactionAsync({
            it.insertOrUpdate(historys)
        }, {
            listener?.let {
                listener.queryResult(success = true, exist = false)
            }
            LTDBManager.getInstance().getRealm().close()
        }, {
            listener?.let {
                listener.queryResult(success = false, exist = false)
            }
            LTDBManager.getInstance().getRealm().close()
        })
    }

    fun insert(history: History) {
        if (select(history.uniqueId) == null) {
            this.insert(history, null)
        }
    }

    fun selectAll() = ArrayList<History>(
        LTDBManager.getInstance().getRealm().copyFromRealm(
            LTDBManager.getInstance().getRealm().where(History::class.java).sort("historyDate", Sort.DESCENDING).findAll()

        )
    )

    fun delete(uniqueId: String, listener: queryListener?) {

        LTDBManager.getInstance().getRealm().executeTransactionAsync({
            it.where(History::class.java).equalTo("uniqueId", uniqueId).findAll().deleteAllFromRealm()
        }, {
            listener?.let {
                listener.queryResult(success = true, exist = false)
            }
            LTDBManager.getInstance().getRealm().close()
        }, {
            listener?.let {
                it.queryResult(false, exist = false)
            }
            LTDBManager.getInstance().getRealm().close()
        })
    }

    fun deleteAll(listener: queryListener?) {
        LTDBManager.getInstance().getRealm().executeTransactionAsync({
            it.delete(History::class.java)
        }, {
            listener?.let {
                it.queryResult(success = true, exist = false)
            }
            LTDBManager.getInstance().getRealm().close()
        }, {
            listener?.let {
                it.queryResult(success = false, exist = false)
            }
            LTDBManager.getInstance().getRealm().close()
        })
    }

    fun select(uniqueId: String): History? = LTDBManager.getInstance().getRealm().where(History::class.java).equalTo("uniqueId", uniqueId).findFirst()


    interface queryListener {
        fun queryResult(success: Boolean, exist: Boolean)
    }

}