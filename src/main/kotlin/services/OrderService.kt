package com.ipeasa.services

interface OrderService {
    fun readAllOrders(pageSize : Int, page : Long) : List
}