package com.example.pokemonapp.di;

import android.app.Application;

import com.example.pokemonapp.network.ApiInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)

public class RetrofitModule {
    @Provides
    @Singleton
    public static ApiInterface providePokemonApiInterface() {
        return new Retrofit.Builder()
                .baseUrl("\n" +
                        "https://pokeapi.co/api/v2/\n")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ApiInterface.class);
    }
}
