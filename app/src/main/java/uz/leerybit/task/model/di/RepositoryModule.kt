package uz.leerybit.task.model.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.leerybit.task.model.ProductRepository
import uz.leerybit.task.model.ProductRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @[Singleton Binds]
    abstract fun getProductRepository(authRepositoryImpl: ProductRepositoryImpl): ProductRepository
}