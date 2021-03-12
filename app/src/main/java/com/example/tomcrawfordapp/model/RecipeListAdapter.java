package com.example.tomcrawfordapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tomcrawfordapp.R;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    class RecipeViewHolder extends RecyclerView.ViewHolder {
        private final TextView foodItemView;

        private RecipeViewHolder(View itemView) {
            super(itemView);
            foodItemView = itemView.findViewById(R.id.recipeTitle);
        }

    }

    private final LayoutInflater mInflater;
    private List<Recipe> mRecipe; // Cached copy of recipe

    public RecipeListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Recipe current = mRecipe.get(position);
        holder.foodItemView.setText(current.getName());

    }

    public void setRecipes(List<Recipe> recipes){
        mRecipe = recipes;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mRecipe has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mRecipe != null)
            return mRecipe.size();
        else return 0;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView type, name, ingredients, notes;
        Button update, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            type = itemView.findViewById(R.id.type);
            ingredients = itemView.findViewById(R.id.ingredients);
            name = itemView.findViewById(R.id.name);
            notes = itemView.findViewById(R.id.notes);
            update = itemView.findViewById(R.id.updateId);
            delete = itemView.findViewById(R.id.deleteId);
        }
    }
}
