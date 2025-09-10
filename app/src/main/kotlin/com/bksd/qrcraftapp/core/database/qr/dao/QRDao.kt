package com.bksd.qrcraftapp.core.database.qr.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.bksd.qrcraftapp.core.database.qr.entity.QREntity
import com.bksd.qrcraftapp.feature.qr.domain.model.QRSource
import kotlinx.coroutines.flow.Flow

@Dao
interface QRDao {
    @Upsert
    suspend fun upsert(qr: QREntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qr: QREntity): Long

    @Query("DELETE FROM qr_table WHERE qrId = :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM qr_table WHERE qrSource = :qrSource ORDER BY timestamp DESC")
    fun observeQRListByType(qrSource: QRSource): Flow<List<QREntity>>

    @Query("SELECT * FROM qr_table WHERE qrId = :id")
    fun getQrEntityById(id: Long): Flow<QREntity>

    @Query("DELETE FROM qr_table")
    suspend fun deleteAll()
}