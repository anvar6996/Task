package uz.leerybit.task.model.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.create
import uz.leerybit.task.model.remote.ProductApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

  @[Provides Singleton]
  fun productApi(retrofit: Retrofit) = retrofit.create<ProductApi>()
}

