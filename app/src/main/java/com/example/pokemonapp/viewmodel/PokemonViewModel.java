package com.example.pokemonapp.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokemonapp.model.PokemonResponse;
import com.example.pokemonapp.model.Result;
import com.example.pokemonapp.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {
    private Repository repository;


    MutableLiveData<ArrayList<Result>> response = new MutableLiveData<ArrayList<Result>>();
    private LiveData<List<Result>> favList = null;

    public LiveData<List<Result>> getFavList() {
        return favList;
    }

    @ViewModelInject

    public PokemonViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Result>> getResponse() {
        return response;
    }


    public void getPokemons() {
        repository.getPokemons()
                .subscribeOn(Schedulers.io())
                .map(new Function<PokemonResponse, ArrayList<Result>>() {
                    @Override
                    public ArrayList<Result> apply(PokemonResponse pokemonResponse) throws Exception {
                        ArrayList<Result> list = pokemonResponse.getResults();
                        for (Result result : list) {
                            String url = result.getUrl();
                            String[] pokemonIndex = url.split("/");
                            result.setUrl("https://pokeres.bastionbot.org/images/pokemon/" + pokemonIndex[pokemonIndex.length - 1] + ".png");
                        }
                        return list;
                    }
                })

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> response.setValue(results),
                        error -> Log.e("viewmodel", error.getMessage()));
    }

    public void insertPokemon(Result result) {
        repository.insertPokemon(result);
    }

    public void deletePokemon(String pokemonName) {
        repository.deletePokemon(pokemonName);
    }

    public void getFavPokemons() {
        favList = repository.getFavPokemons();
    }

    ;
}
