package edu.sfsu.thecocktaildb.ui.dashboard;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.annotation.SuppressLint;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import edu.sfsu.thecocktaildb.Model.ArtistModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DashboardViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<ArtistModel>> mutableLiveData;
    public static ArrayList<ArtistModel> model;

    public DashboardViewModel() {
        mutableLiveData = new MutableLiveData<>();
        model = new ArrayList<>();

        new ArtistAsyncTask().execute("https://www.theaudiodb.com/api/v1/json/123/search.php?s=nas");
    }

    public LiveData<ArrayList<ArtistModel>> getLiveData() {
        return mutableLiveData;
    }

    public ArrayList<ArtistModel> getModel() {
        return model;
    }

    public class ArtistAsyncTask extends AsyncTask<String, String, String> {

        /**
         * protected void onPreExecute()
         * Runs on the UI thread before doInBackground(Params).
         * Invoked by execute(Params) or executeOnExecutor(Executor, Params).
         * The default version does nothing.
         * This method must be called from the main thread of your app.
         */
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("dash", "onPreExecute()" );
        }

        @Override
        protected String doInBackground(String... param) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(param[0]).build();

            Log.v("dash", "ArtistAsyncTask - doInBackground()");

            try {
                Response response = client.newCall(request).execute();
                if(!response.isSuccessful())
                    return null;

                assert response.body() != null;
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.v("dash", "onPostExecute()" + result);
            super.onPostExecute(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray obj = jsonObject.getJSONArray("artists");

                for (int i = 0; i < obj.length(); i++) {
                    model.add(new ArtistModel(
                            obj.getJSONObject(i).getString("idArtist"),
                            obj.getJSONObject(i).getString("strArtist"),
                            obj.getJSONObject(i).getString("strArtistStripped"),
                            obj.getJSONObject(i).getString("strArtistAlternate"),
                            obj.getJSONObject(i).getString("strLabel"),
                            obj.getJSONObject(i).getString("idLabel"),
                            obj.getJSONObject(i).getString("intFormedYear"),
                            obj.getJSONObject(i).getString("intBornYear"),
                            obj.getJSONObject(i).getString("intDiedYear"),
                            obj.getJSONObject(i).getString("strDisbanded"),
                            obj.getJSONObject(i).getString("strStyle"),
                            obj.getJSONObject(i).getString("strGenre"),
                            obj.getJSONObject(i).getString("strMood"),
                            obj.getJSONObject(i).getString("strWebsite"),
                            obj.getJSONObject(i).getString("strFacebook"),
                            obj.getJSONObject(i).getString("strTwitter"),
                            obj.getJSONObject(i).getString("strBiographyEN"),
                            obj.getJSONObject(i).getString("strBiographyDE"),
                            obj.getJSONObject(i).getString("strBiographyFR"),
                            obj.getJSONObject(i).getString("strBiographyCN"),
                            obj.getJSONObject(i).getString("strBiographyIT"),
                            obj.getJSONObject(i).getString("strBiographyJP"),
                            obj.getJSONObject(i).getString("strBiographyRU"),
                            obj.getJSONObject(i).getString("strBiographyES"),
                            obj.getJSONObject(i).getString("strBiographyPT"),
                            obj.getJSONObject(i).getString("strBiographySE"),
                            obj.getJSONObject(i).getString("strBiographyNL"),
                            obj.getJSONObject(i).getString("strBiographyHU"),
                            obj.getJSONObject(i).getString("strBiographyNO"),
                            obj.getJSONObject(i).getString("strBiographyIL"),
                            obj.getJSONObject(i).getString("strBiographyPL"),
                            obj.getJSONObject(i).getString("strGender"),
                            obj.getJSONObject(i).getString("intMembers"),
                            obj.getJSONObject(i).getString("strCountry"),
                            obj.getJSONObject(i).getString("strCountryCode"),
                            obj.getJSONObject(i).getString("strArtistThumb"),
                            obj.getJSONObject(i).getString("strArtistLogo"),
                            obj.getJSONObject(i).getString("strArtistCutout"),
                            obj.getJSONObject(i).getString("strArtistClearart"),
                            obj.getJSONObject(i).getString("strArtistWideThumb"),
                            obj.getJSONObject(i).getString("strArtistFanart"),
                            obj.getJSONObject(i).getString("strArtistFanart2"),
                            obj.getJSONObject(i).getString("strArtistFanart3"),
                            obj.getJSONObject(i).getString("strArtistFanart4"),
                            obj.getJSONObject(i).getString("strArtistBanner"),
                            obj.getJSONObject(i).getString("strMusicBrainzID"),
                            obj.getJSONObject(i).getString("strISNIcode"),
                            obj.getJSONObject(i).getString("strLastFMChart"),
                            obj.getJSONObject(i).getString("intCharted"),
                            obj.getJSONObject(i).getString("strLocked")));
                }
                mutableLiveData.setValue(model);
            } catch(JSONException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}