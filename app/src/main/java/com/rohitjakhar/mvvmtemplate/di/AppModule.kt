package com.rohitjakhar.mvvmtemplate.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.rohitjakhar.mvvmtemplate.data.remote.webservice.AuthInterceptor
import com.rohitjakhar.mvvmtemplate.data.remote.webservice.WebService
import com.rohitjakhar.mvvmtemplate.data.repository.DataRepoImpl
import com.rohitjakhar.mvvmtemplate.domain.repository.DataRepo
import com.rohitjakhar.mvvmtemplate.presentation.home.HomeViewModel
import com.rohitjakhar.mvvmtemplate.util.Constant.BASE_URL
import com.rohitjakhar.mvvmtemplate.util.Constant.DATA_STORE_NAME
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val mainModule = module {

    single {
        PreferenceDataStoreFactory.create {
            androidContext().preferencesDataStoreFile(
                DATA_STORE_NAME
            )
        }
    }
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        AuthInterceptor()
    }

    single {
        OkHttpClient.Builder().addInterceptor(get<HttpLoggingInterceptor>())
            .callTimeout(600, TimeUnit.SECONDS)
            .readTimeout(600, TimeUnit.SECONDS)
            .connectTimeout(10000, TimeUnit.SECONDS)
            .build()
    }

    single<WebService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .callFactory(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }
    single<DataRepo> { DataRepoImpl(get()) }
    viewModel {
        HomeViewModel()
    }
}
