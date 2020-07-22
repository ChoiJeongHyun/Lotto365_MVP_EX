package kr.co.example.lotto365.miniframework.database.dbmanager

import android.content.Context
import io.realm.DynamicRealm
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmMigration
import io.realm.annotations.RealmModule


abstract class DBManager(context: Context) {

    private val realmConfiguration: RealmConfiguration
    private var realm: Realm

    init {
        Realm.init(context)
        realmConfiguration = createDatabase()
        realm = Realm.getInstance(realmConfiguration)
    }


    abstract protected open fun createDatabase(): RealmConfiguration

    abstract protected open fun getDatabaseName(): String

    abstract protected open fun getDatabaseVersion(): Int

    abstract protected open fun updateDatabase(realm: DynamicRealm, oldVersion: Long, newVersion: Long)

    open fun getRealm(): Realm {
        return if (realm.isClosed) Realm.getInstance(realmConfiguration).also { realm = it } else realm
    }


    inner class Migration : RealmMigration {
        override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
            updateDatabase(realm, oldVersion, newVersion)
        }

    }

    @RealmModule(allClasses = true)
    inner class Modules


}