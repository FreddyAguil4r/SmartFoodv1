package com.example.smartfood.Model

data class Product(
    val Id: Long,
    val name : String,
    val date_purchase: String,
    val due_date : String,
    val unit_cost : Double,
    val amount : Double,
    val value : Double,
)