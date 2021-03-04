package com.jovandy.testrs.api


import com.jovandy.testrs.api.respone.createdata.PostCreate
import com.jovandy.testrs.api.respone.createdata.ResponseCreateData
import com.jovandy.testrs.api.respone.deletedata.ResponseDeleteData
import com.jovandy.testrs.api.respone.listdata.ResponseListData
import com.jovandy.testrs.api.respone.updatedata.PostUpdate
import com.jovandy.testrs.api.respone.updatedata.ResponseUpdateData
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("posts")
    fun getListData(): Call<ResponseListData>


    @POST("posts")
    fun saveItem(
        @Body body: PostCreate
    ): Call<ResponseCreateData>


    @PUT("posts/{id}")
    fun updateItem(
        @Path("id") id: String?,
        @Body body: PostUpdate
    ): Call<ResponseUpdateData>


    @DELETE("posts/{id}")
    fun deleteItem(@Path("id") id: String?): Call<ResponseDeleteData>
}
