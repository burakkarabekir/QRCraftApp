package com.bksd.qrcraftapp.feature.qr.data.qr.data_source

import com.bksd.qrcraftapp.core.database.qr.dao.QRDao
import com.bksd.qrcraftapp.feature.qr.data.qr.mapper.toEntity
import com.bksd.qrcraftapp.feature.qr.data.qr.mapper.toQR
import com.bksd.qrcraftapp.feature.qr.domain.data_source.QRDataSource
import com.bksd.qrcraftapp.feature.qr.domain.model.QR
import com.bksd.qrcraftapp.feature.qr.domain.model.QRSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class RoomQRDataSource(
    private val qrDao: QRDao,
) : QRDataSource {
    override suspend fun upsert(qr: QR) {
        runCatching {
            qrDao.upsert(qr.toEntity())
            Timber.d("QR upsert :: ${qr.title}")
        }
            .onFailure { Timber.e(it, "Failed to upsert qr") }
    }

    override suspend fun insert(qr: QR): Long {
        return runCatching {
            Timber.d("QR insert :: ${qr.title}")
            qrDao.insert(qr.toEntity())
        }
            .onFailure { Timber.e(it, "Failed to insert qr") }
            .getOrThrow()
    }

    override suspend fun delete(id: Long) {
        runCatching { qrDao.delete(id) }
            .onFailure { Timber.e(it, "Failed to delete qr") }
    }

    override fun observeQRList(): Flow<List<QR>> {
        return qrDao
            .observeQRList()
            .map { entities -> entities.map { it.toQR() } }
    }

    override fun getQrEntityById(id: Long): Flow<QR> {
        return qrDao
            .getQrEntityById(id)
            .map { entity -> entity.toQR() }
    }
}