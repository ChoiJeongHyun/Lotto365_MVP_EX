package kr.co.example.lotto365.miniframework.database.dbmanager

import android.content.Context
import io.realm.DynamicRealm
import io.realm.RealmConfiguration

class LTDBManager(context: Context) : DBManager(context) {

    companion object {
        private val DATABASE_NAME = "LottoDataBase"
        private lateinit var instance: LTDBManager

        fun getInstance(): LTDBManager {
            return synchronized(LTDBManager::class.java) {
                instance
            }
        }
    }

    init {
        instance = this
    }


    override fun createDatabase(): RealmConfiguration {
        return RealmConfiguration.Builder().name("Lotto.realm").schemaVersion(1).migration(Migration()).modules(Modules()).build()
    }

    override fun getDatabaseName() = DATABASE_NAME

    override fun getDatabaseVersion() = 1

    override fun updateDatabase(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {

    }


}