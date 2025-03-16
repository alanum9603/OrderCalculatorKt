package com.ipeasa

import com.ipeasa.repositories.MaterialRepository
import com.ipeasa.repositories.MaterialRepositoryImpl
import com.ipeasa.services.MaterialService
import com.ipeasa.services.MaterialServiceImpl
import io.ktor.server.application.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.ktor.plugin.Koin
import org.koin.dsl.module
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }
}

val appModule = module {
    single<MaterialRepository> { MaterialRepositoryImpl() }
    single<MaterialService> { MaterialServiceImpl(get()) }
}