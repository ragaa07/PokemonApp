package com.example.pokemonapp.dp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pokemonapp.model.Result;

@Database(entities = Result.class,version = 1,exportSchema = false)
public abstract class  PokemonDb extends RoomDatabase {
    public  abstract  PokemonDao pokemonDao();

}
