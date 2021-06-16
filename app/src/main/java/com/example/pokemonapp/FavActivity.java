package com.example.pokemonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pokemonapp.adapters.PokemonAdapter;
import com.example.pokemonapp.model.Result;
import com.example.pokemonapp.viewmodel.PokemonViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class FavActivity extends AppCompatActivity {
    private PokemonViewModel viewModel;
    private RecyclerView recyclerView;
    private PokemonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        recyclerView = findViewById(R.id.favRecyclerView);
        adapter = new PokemonAdapter(this);
        recyclerView.setAdapter(adapter);
        setupSwipe();
        viewModel=new ViewModelProvider(this).get(PokemonViewModel.class);
        viewModel.getFavPokemons();
        viewModel.getFavList().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(List<Result> results) {
                ArrayList<Result>arrayList=new ArrayList<>();
                arrayList.addAll(results);
                adapter.setPokemons(arrayList);
            }
        });
    }

    public void setupSwipe()
    {
        ItemTouchHelper.SimpleCallback callback= new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPokemonPosition=viewHolder.getAdapterPosition();
                Result swipedPokemon=adapter.getPoke(swipedPokemonPosition);
                viewModel.deletePokemon(swipedPokemon.getName());
                adapter.notifyDataSetChanged();

            }
        };
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}