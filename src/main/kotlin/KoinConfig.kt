package com.ipeasa

import com.ipeasa.dtos.material.DtoMapperMaterial
import com.ipeasa.dtos.material.DtoMapperMaterialImpl
import com.ipeasa.dtos.order.DtoMapperOrder
import com.ipeasa.dtos.order.DtoMapperOrderImpl
import com.ipeasa.dtos.product.DtoMapperProduct
import com.ipeasa.dtos.product.DtoMapperProductImpl
import com.ipeasa.repositories.*
import com.ipeasa.services.*
import com.ipeasa.utils.UuidService
import io.ktor.server.application.*
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
    single<ProductRepository> { ProductRepositoryImpl(get(), get()) }
    single<OrderRepository> { OrderRepositoryImpl(get(), get()) }

    single<DtoMapperMaterial> { DtoMapperMaterialImpl() }
    single<DtoMapperProduct> { DtoMapperProductImpl(get()) }
    single<DtoMapperOrder> { DtoMapperOrderImpl() }

    single<MaterialService> { MaterialServiceImpl(get(), get()) }
    single<ProductService> { ProductServiceImpl(get(), get()) }
    single<OrderService> { OrderServiceImpl(get()) }
}