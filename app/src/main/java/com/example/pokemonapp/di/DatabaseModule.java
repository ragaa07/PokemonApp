package com.example.pokemonapp.di;

import android.app.Application;

import androidx.room.Room;

import com.example.pokemonapp.dp.PokemonDao;
import com.example.pokemonapp.dp.PokemonDb;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {
    @Provides
    @Singleton
    public static PokemonDb provideDb(Application application)
    {
        return Room.databaseBuilder(application,PokemonDb.class,"fav_DB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
    @Provides
    @Singleton
    public static PokemonDao provideDao(PokemonDb pokemonDb)
    {
        return pokemonDb.pokemonDao();
    }
}
