package com.ipeasa

import com.ipeasa.repositories.MaterialRepository
import com.ipeasa.repositories.MaterialRepositoryImpl
import com.ipeasa.services.MaterialService
import com.ipeasa.services.MaterialServiceImpl
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

object KoinConfig {
    fun init() {
        startKoin {
            modules(appModule)
        }
    }
}

val appModule : Module = module {
    single<MaterialRepository> { MaterialRepositoryImpl() }
    single<MaterialService> { MaterialServiceImpl() }
}