package com.bksd.qrcraftapp.feature.qr.domain.data_source

import com.bksd.qrcraftapp.feature.qr.domain.model.QR
import kotlinx.coroutines.flow.Flow

interface QRDataSource {
    suspend fun upsert(qr: QR)
    suspend fun insert(qr: QR): Long
    suspend fun delete(id: Long)
    fun observeQRList(): Flow<List<QR>>
    fun getQrEntityById(id: Long): Flow<QR>
}