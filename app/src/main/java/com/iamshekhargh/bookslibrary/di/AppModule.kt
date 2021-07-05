package com.iamshekhargh.bookslibrary.di

import com.iamshekhargh.bookslibrary.network.ApiInterface
import com.iamshekhargh.bookslibrary.repo.BooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by <<-- iamShekharGH -->>
 * on 05 July 2021, Monday
 * at 10:34 PM
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideReposotory(apiInterface: ApiInterface) = BooksRepository(apiInterface)


    // Retrofit ----------->
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiInterface.BASE_URL).build()

    @Provides
    @Singleton
    fun provideGithubApi(retrofit: Retrofit) = retrofit.create(ApiInterface::class.java)

    // <-------------------- Retrofit
}