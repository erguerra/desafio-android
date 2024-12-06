package com.picpay.desafio.android.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.data.local.PicPayDatabase
import com.picpay.desafio.android.data.network.PicPayService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesGsonInstance(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun providesRetrofitInstance(
        gson: Gson,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun providesPicPayService(retrofit: Retrofit): PicPayService {
        return retrofit.create(PicPayService::class.java)
    }

    @Provides
    @IODispatcher
    fun providesNetworkingDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext context: Context): PicPayDatabase {
        return Room.databaseBuilder(
            context = context,
            PicPayDatabase::class.java,
            "PicPay.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesUserDao(database: PicPayDatabase) = database.userDao()


    private const val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
}