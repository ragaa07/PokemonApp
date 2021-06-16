package com.example.pokemonapp.dp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pokemonapp.model.Result;

import java.util.List;

@Dao
public interface PokemonDao {
    @Insert
    public void insertPokemon(Result result);


    @Query("delete from fav_table where name=:pokemonName")
    public void deletePokemon(String pokemonName);


    @Query("select * from fav_table")
    public LiveData<List<Result>> getPokemons();
}
