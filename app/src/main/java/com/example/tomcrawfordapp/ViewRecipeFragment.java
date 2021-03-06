package com.example.tomcrawfordapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tomcrawfordapp.model.Recipe;
import com.example.tomcrawfordapp.model.RecipeListAdapter;
import com.example.tomcrawfordapp.model.RecipeViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewRecipeFragment extends Fragment {
    private RecipeViewModel mRecipeViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_recipe, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewRecipeFragmentArgs args = ViewRecipeFragmentArgs.fromBundle(getArguments());
        String mealName = args.getMealName();
        TextView title = view.findViewById(R.id.label_food);
        title.setText(mealName);

        Button button = view.findViewById(R.id.create_food_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ViewRecipeFragment.this)
                        .navigate(R.id.CreateRecipeFragment, null);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_food);
        RecipeListAdapter adapter = new RecipeListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
//        if statement - if meal type breakfast, getAllBreakfast.observe - else if lunch etc...

        mRecipeViewModel.getAllMeals(mealName).observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
                @Override
                public void onChanged(@Nullable final List<Recipe> recipes) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setRecipes(recipes);
                }
            });



    }
}