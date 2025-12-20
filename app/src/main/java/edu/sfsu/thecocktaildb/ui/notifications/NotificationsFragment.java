package edu.sfsu.thecocktaildb.ui.notifications;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.sfsu.thecocktaildb.Adapter.RecyclerViewAdapter;
import edu.sfsu.thecocktaildb.Model.ArtistModel;
import edu.sfsu.thecocktaildb.R;
import edu.sfsu.thecocktaildb.databinding.FragmentHomeBinding;
import edu.sfsu.thecocktaildb.databinding.FragmentNotificationsBinding;
import edu.sfsu.thecocktaildb.ui.home.HomeViewModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public RecyclerView recyclerView;
    static final String URL = "https://www.theaudiodb.com/api/v1/json/123/search.php?s=rza";
    public ArrayList<ArtistModel> model;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        model = new ArrayList<>();

        NotificationsViewModel vm = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);

//        View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        // TextView textView = root.findViewById(R.id.text_notifications);
        TextView textView = view.findViewById(R.id.text_notifications);
        vm.getText().observe(getViewLifecycleOwner(), textView::setText);

        new AsyncTaskCategory().execute(URL);

        recyclerView = view.findViewById(R.id.id_recycler_view);

        NotificationsViewModel notificationViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);

        return view;
    }

    public final @SuppressLint("SimpleDateFormat") SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd-HH:mm");

    /**
     * WriteReadRunnable
     * Writes result (JSON) to disk in a new thread.
     */
    public class WriteReadJSON implements Runnable {

        private final String result;

        public WriteReadJSON(String result) {
            this.result = result;
        }

        public String getResult() {
            return result;
        }

        final Handler handler = new Handler();

        @Override
        public synchronized void run() {
            try {
                Log.d("log", "requiredContext().getFilesDir() [ " + requireContext().getFilesDir() + " ]");

                File file = new File(requireContext().getFilesDir(), fmt.format(new Date()) + "_notification.txt");
                Log.d("log", "File Date -> " + file.getPath());
                FileWriter fileWriter = new FileWriter(file);

                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                Log.d("log", "result.toString() -> " + getResult());
                bufferedWriter.write(getResult());
                bufferedWriter.close();

                // Read JSON response from disk.
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line = bufferedReader.readLine();

                while(line != null) {
                    stringBuilder.append(line).append("\n");
                    line = bufferedReader.readLine();
                }

                bufferedReader.close();

                // This is being populated from disk.
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONArray obj = jsonObject.getJSONArray("artists");

                Log.d("log", "stringBuilder -> " + stringBuilder);

                for(int i =  0; i < obj.length(); i++) {
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
            } catch (JSONException | IOException e) {
                throw new RuntimeException(e);
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    Log.d("log", "INSIDE OF RUN RUN RUN()");
                    recyclerView.setAdapter(new RecyclerViewAdapter(model));
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                }
            });
        }
    }

    public class AsyncTaskCategory extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            Log.d("log", "[ onPreExecute ]");
        }

        @Override
        protected String doInBackground(String... strings) {

            Log.d("log", "[ doInBackground ]");

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(strings[0]).build();

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
            super.onPostExecute(result);

            Thread threadWriteJSONRunnable  = new Thread(new WriteReadJSON(result));
            threadWriteJSONRunnable.start();

            try {
                threadWriteJSONRunnable.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // (new Thread(new WriteJSONRunnable(result))).start();
            /*
                 The below code works.
                 WriteJSONRunnable writeRunnable = new WriteJSONRunnable(result);
                 (new Thread(writeRunnable)).start();
             */
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}