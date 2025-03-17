package com.ipeasa

import com.ipeasa.repositories.MaterialRepository
import com.ipeasa.repositories.MaterialRepositoryImpl
import com.ipeasa.repositories.ProductRepository
import com.ipeasa.repositories.ProductRepositoryImpl
import com.ipeasa.services.MaterialService
import com.ipeasa.services.MaterialServiceImpl
import com.ipeasa.services.ProductService
import com.ipeasa.services.ProductServiceImpl
import com.ipeasa.utils.UuidService
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
    single { UuidService() }
    single<MaterialRepository> { MaterialRepositoryImpl(get()) }
    single<MaterialService> { MaterialServiceImpl(get()) }
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    single<ProductService> { ProductServiceImpl(get()) }
}