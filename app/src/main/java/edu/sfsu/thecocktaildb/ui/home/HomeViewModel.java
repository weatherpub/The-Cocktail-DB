package edu.sfsu.thecocktaildb.ui.home;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
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

import edu.sfsu.thecocktaildb.Model.CocktailModel;

/**
 * Make an HTTP Request and pass the data to LiveData to update the Fragment
 */
import edu.sfsu.thecocktaildb.Model.CocktailModel;
// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *  HomeViewModel
public class HomeViewModel extends ViewModel {

    // Usually MutableLiveData<>() is used in the ViewModel,
    // and then the ViewModel only exposes immutable LiveData objects to observers.
    private MutableLiveData<CocktailModel> liveData;

    private static final String URL = "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita";

    public HomeViewModel() {
        liveData = new MutableLiveData<>(new CocktailModel(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        ));
        Log.v("log", "HomeViewModel is initialized");

        new CocktailAsyncTask().execute(URL);
    }

    // Return a copy of the liveData presumably populated by onPostExecute below.
    public LiveData<CocktailModel> getLiveData() {
        return liveData;
    }

    public class CocktailAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.v("log", "onPreExecute() in HomeViewModel");
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

                // FileOutputStream out = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
                //byte[] buffer = new byte[1024];
                // int bytesRead = bufferedReader.read(buffer);

                StringBuilder builder = new StringBuilder();

                while((line = bufferedReader.readLine()) != null) {
                    builder.append(line).append("\n");
                    // out.write(1221);
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
        /**
         * onPostExecute - executes on the main thread.
         * @param result
         */
        @Override
        protected void onPostExecute(String result) { // onPostExecute - runs on the main thread.
            super.onPostExecute(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray obj = jsonObject.getJSONArray("drinks");

                /**
                 * liveData is being set with the results from the JSON call
                 */
                for(int i =  0; i < obj.length(); i++) {
                    liveData.setValue(new CocktailModel(
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
        }
    }
}