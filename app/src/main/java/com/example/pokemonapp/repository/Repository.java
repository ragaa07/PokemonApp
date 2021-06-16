package com.example.pokemonapp.repository;

import androidx.lifecycle.LiveData;

import com.example.pokemonapp.dp.PokemonDao;
import com.example.pokemonapp.model.PokemonResponse;
import com.example.pokemonapp.model.Result;
import com.example.pokemonapp.network.ApiInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {

    private ApiInterface apiInterface;
    private PokemonDao pokemonDao;

    @Inject
    public Repository(ApiInterface apiInterface, PokemonDao pokemonDao) {
        this.apiInterface = apiInterface;
        this.pokemonDao = pokemonDao;
    }

    public Observable<PokemonResponse> getPokemons() {
        return apiInterface.getPokemons();
    }

    public void insertPokemon(Result result) {
        pokemonDao.insertPokemon(result);
    }

    public void deletePokemon(String pokemonName) {
        pokemonDao.deletePokemon(pokemonName);
    }

    public LiveData<List<Result>> getFavPokemons() {
        return pokemonDao.getPokemons();
    }
}
