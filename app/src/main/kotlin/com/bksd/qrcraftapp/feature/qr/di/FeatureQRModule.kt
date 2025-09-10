package com.bksd.qrcraftapp.feature.qr.di

import com.bksd.qrcraftapp.feature.qr.data.qr.data_source.RoomQRDataSource
import com.bksd.qrcraftapp.feature.qr.domain.data_source.QRDataSource
import com.bksd.qrcraftapp.feature.qr.ui.create_qr.form.CreateQrFormViewModel
import com.bksd.qrcraftapp.feature.qr.ui.create_qr.selection.CreateQRSelectionViewModel
import com.bksd.qrcraftapp.feature.qr.ui.history.HistoryViewModel
import com.bksd.qrcraftapp.feature.qr.ui.history.scanned_tab.ScannedTabViewModel
import com.bksd.qrcraftapp.feature.qr.ui.main.MainViewModel
import com.bksd.qrcraftapp.feature.qr.ui.scan_result.ScanResultViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val featureQRModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::CreateQRSelectionViewModel)
    viewModelOf(::HistoryViewModel)
    viewModelOf(::ScanResultViewModel)
    viewModelOf(::CreateQrFormViewModel)
    viewModelOf(::ScannedTabViewModel)
    singleOf(::RoomQRDataSource) bind QRDataSource::class
}