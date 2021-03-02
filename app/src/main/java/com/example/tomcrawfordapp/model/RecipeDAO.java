package com.example.tomcrawfordapp.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface RecipeDAO {
    @Insert
    void insert(Recipe recipe);

    @Query("DELETE FROM recipe")
    void deleteAll();
//3 different selects. getAllBreakfast etc ->order by meal type.
    @Query("SELECT * from recipe ORDER BY title ASC")
    LiveData<List<Recipe>> getAllRecipe();
}
