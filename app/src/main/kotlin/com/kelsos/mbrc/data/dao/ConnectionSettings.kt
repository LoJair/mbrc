package com.kelsos.mbrc.data.dao

import com.kelsos.mbrc.data.db.RemoteDatabase
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.ConflictAction
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.annotation.Unique
import com.raizlabs.android.dbflow.annotation.UniqueGroup
import com.raizlabs.android.dbflow.kotlinextensions.modelAdapter
import com.raizlabs.android.dbflow.structure.Model


@Table(name = "settings",
    database = RemoteDatabase::class,
    uniqueColumnGroups = arrayOf(UniqueGroup(groupNumber = 1, uniqueConflict = ConflictAction.IGNORE)))
data class ConnectionSettings(@Column(name = "address")
                              @Unique(unique = false, uniqueGroups = intArrayOf(1))
                              var address: String? = null,
                              @Unique(unique = false, uniqueGroups = intArrayOf(1))
                              @Column(name = "port")
                              var port: Int = 0,
                              @Column(name = "name")
                              var name: String? = null,
                              @PrimaryKey(autoincrement = true)
                              @Column(name = "id")
                              var id: Long = 0) : Model {
  /**
   * Loads from the database the most recent version of the model based on it's primary keys.
   */
  override fun load() {
    modelAdapter<ConnectionSettings>().load(this)
  }

  override fun insert(): Long {
    return modelAdapter<ConnectionSettings>().insert(this)
  }

  override fun save() {
    modelAdapter<ConnectionSettings>().save(this)
  }

  override fun update() {
    modelAdapter<ConnectionSettings>().update(this)
  }

  override fun exists(): Boolean {
    return modelAdapter<ConnectionSettings>().exists(this)
  }

  override fun delete() {
    modelAdapter<ConnectionSettings>().delete(this)
  }

}
