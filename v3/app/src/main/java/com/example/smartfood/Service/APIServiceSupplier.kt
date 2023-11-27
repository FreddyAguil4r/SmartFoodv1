package com.example.smartfood.Service

import com.example.smartfood.ModelResponse.SupplierResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface APIServiceSupplier {
    @GET
    suspend fun getSupplierById(@Url url:String): Response<SupplierResponse>

    @GET
    suspend fun getAllSuplier(@Url url:String): Response<SupplierResponse>
}