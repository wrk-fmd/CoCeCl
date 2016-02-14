package it.fmd.cocecl.utilclass;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.fmd.cocecl.patadminaction.PatData;

public class SharedPreference {

    public static final String PREFS_NAME = "PATDATA";

    public SharedPreference() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<PatData> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        JSONObject json = new JSONObject();
        String jsonFavorites = json.toString(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, PatData product) {
        List<PatData> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<PatData>();
        favorites.add(product);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, PatData product) {
        ArrayList<PatData> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(product);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<PatData> getFavorites(Context context) {
        SharedPreferences settings;
        List<PatData> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Json json = new Json();
            PatData[] favoriteItems = json.fromJson(jsonFavorites,
                    PatData[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<PatData>(favorites);
        } else
            return null;

        return (ArrayList<PatData>) favorites;
    }
}