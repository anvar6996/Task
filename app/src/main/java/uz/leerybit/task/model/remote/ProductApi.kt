package uz.leerybit.task.model.remote

import retrofit2.http.GET
import uz.leerybit.task.model.responce.ProductResponce

interface ProductApi {
  @GET("offers")
  suspend fun getProducts(): ProductResponce
}