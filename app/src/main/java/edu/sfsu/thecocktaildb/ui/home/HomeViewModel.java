package edu.sfsu.thecocktaildb.ui.home;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import edu.sfsu.thecocktaildb.Model.CocktailModel;
import edu.sfsu.thecocktaildb.ViewModel.CocktailViewModel;

// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  HomeViewModel
public class HomeViewModel extends ViewModel {
    public MutableLiveData<ArrayList<CocktailModel>> liveData;
    public CocktailViewModel cocktailViewModel = CocktailViewModel.getInstance();
    public ArrayList<CocktailModel> model = cocktailViewModel.getData();
    public HashMap<String, String> url;

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  Constructor
    public HomeViewModel() {
        url = new HashMap<>();
        liveData = new MutableLiveData<>();
        url.put("drink", "daiquiri");
        new CocktailAsyncTask().execute("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + url.get("drink"));
    }

    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  CockTailAsyncTask
    public class CocktailAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.v("log", "onPreExecute()");
        }

        // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  doInBackground
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection httpURLConnection = null;
            String line = null;
            String results = null;

            Log.i("log", params[0]);

            try {
                URL url = new URL(params[0]);

                httpURLConnection = (HttpURLConnection) url.openConnection();

                int code = httpURLConnection.getResponseCode();

                if(code != 200)
                    throw new IOException("Invalid response from the server: " + code);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                StringBuilder builder = new StringBuilder();

                while((line = bufferedReader.readLine()) != null) {
                    builder.append(line).append("\n");
                    Log.i("log", "line => " + line);
                }

                if(builder.length() == 0) {
                    return null;
                }

                results = builder.toString();

                return results;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }

            return null;
        }

        // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  onPostExecute
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray obj = jsonObject.getJSONArray("drinks");

                for(int i = 0; i < obj.length(); i++) {
                    Log.i("log", "obj.get(i) => " + obj.get(i)); // Write new objects to console.
                    model.add(new CocktailModel( // Update model with json data.
                            obj.getJSONObject(i).getString("idDrink"),
                            obj.getJSONObject(i).getString("strDrink"),
                            obj.getJSONObject(i).getString("strDrinkAlternate"),
                            obj.getJSONObject(i).getString("strTags"),
                            obj.getJSONObject(i).getString("strVideo"),
                            obj.getJSONObject(i).getString("strCategory"),
                            obj.getJSONObject(i).getString("strIBA"),
                            obj.getJSONObject(i).getString("strAlcoholic"),
                            obj.getJSONObject(i).getString("strGlass"),
                            obj.getJSONObject(i).getString("strInstructions"),
                            obj.getJSONObject(i).getString("strInstructionsES"),
                            obj.getJSONObject(i).getString("strInstructionsDE"),
                            obj.getJSONObject(i).getString("strInstructionsFR"),
                            obj.getJSONObject(i).getString("strInstructionsIT"),
                            obj.getJSONObject(i).getString("strInstructionsZH-HANS"),
                            obj.getJSONObject(i).getString("strInstructionsZH-HANT"),
                            obj.getJSONObject(i).getString("strDrinkThumb"),
                            obj.getJSONObject(i).getString("strIngredient1"),
                            obj.getJSONObject(i).getString("strIngredient2"),
                            obj.getJSONObject(i).getString("strIngredient3"),
                            obj.getJSONObject(i).getString("strIngredient4"),
                            obj.getJSONObject(i).getString("strIngredient5"),
                            obj.getJSONObject(i).getString("strIngredient6"),
                            obj.getJSONObject(i).getString("strIngredient7"),
                            obj.getJSONObject(i).getString("strIngredient8"),
                            obj.getJSONObject(i).getString("strIngredient9"),
                            obj.getJSONObject(i).getString("strIngredient10"),
                            obj.getJSONObject(i).getString("strIngredient11"),
                            obj.getJSONObject(i).getString("strIngredient12"),
                            obj.getJSONObject(i).getString("strIngredient13"),
                            obj.getJSONObject(i).getString("strIngredient14"),
                            obj.getJSONObject(i).getString("strIngredient15"),
                            obj.getJSONObject(i).getString("strMeasure1"),
                            obj.getJSONObject(i).getString("strMeasure2"),
                            obj.getJSONObject(i).getString("strMeasure3"),
                            obj.getJSONObject(i).getString("strMeasure4"),
                            obj.getJSONObject(i).getString("strMeasure5"),
                            obj.getJSONObject(i).getString("strMeasure6"),
                            obj.getJSONObject(i).getString("strMeasure7"),
                            obj.getJSONObject(i).getString("strMeasure8"),
                            obj.getJSONObject(i).getString("strMeasure9"),
                            obj.getJSONObject(i).getString("strMeasure10"),
                            obj.getJSONObject(i).getString("strMeasure11"),
                            obj.getJSONObject(i).getString("strMeasure12"),
                            obj.getJSONObject(i).getString("strMeasure13"),
                            obj.getJSONObject(i).getString("strMeasure14"),
                            obj.getJSONObject(i).getString("strMeasure15"),
                            obj.getJSONObject(i).getString("strImageSource"),
                            obj.getJSONObject(i).getString("strImageAttribution"),
                            obj.getJSONObject(i).getString("strCreativeCommonsConfirmed"),
                            obj.getJSONObject(i).getString("dateModified")));
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            // this.recyclerView.setLayoutManager(new LinearLayoutManager(context));
            //this.recyclerView.setAdapter(new WineAdapter(model));
        }
    }
}