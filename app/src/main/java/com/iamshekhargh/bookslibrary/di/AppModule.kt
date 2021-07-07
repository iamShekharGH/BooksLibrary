package com.iamshekhargh.bookslibrary.di

import android.app.Application
import androidx.room.Room
import com.iamshekhargh.bookslibrary.db.BooksDao
import com.iamshekhargh.bookslibrary.db.BooksDatabase
import com.iamshekhargh.bookslibrary.network.ApiInterface
import com.iamshekhargh.bookslibrary.repo.BooksRepository
import com.iamshekhargh.bookslibrary.util.Constants
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
    fun provideRepository(apiInterface: ApiInterface, booksDao: BooksDao) =
        BooksRepository(apiInterface, booksDao)

    // Room ----------->
    @Provides
    @Singleton
    fun providesDatabase(app: Application) =
        Room.databaseBuilder(app, BooksDatabase::class.java, "books_table")
            .fallbackToDestructiveMigration().build()

    @Provides
    fun providesBooksDao(database: BooksDatabase) = database.getBooksDao()

    @Provides
    fun providesResultDao(database: BooksDatabase) = database.getResultDao()

    // <----------------- Room

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
            .baseUrl(Constants.BASE_URL).build()

    @Provides
    @Singleton
    fun provideGithubApi(retrofit: Retrofit) = retrofit.create(ApiInterface::class.java)

    // <----------------- Retrofit
}