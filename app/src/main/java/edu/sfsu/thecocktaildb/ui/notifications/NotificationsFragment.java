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

import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.sfsu.thecocktaildb.R;
import edu.sfsu.thecocktaildb.databinding.FragmentNotificationsBinding;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    static final String URL = "https://www.theaudiodb.com/api/v1/json/123/search.php?s=rza";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel vm = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        TextView textView = root.findViewById(R.id.text_notifications);
        vm.getText().observe(getViewLifecycleOwner(), textView::setText);

        new AsyncTaskCategory().execute(URL);

        return root;
    }

    /**
     * WriteRunnable
     * Writes result (JSON) to disk in a new thread.
     */
    public class WriteRunnable implements Runnable {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        private final String result;

        public WriteRunnable(String result) {
            this.result = result;
        }

        public String getResult() {
            return result;
        }

        @Override
        public void run() {

            try {
                Log.d("log", "requiredContext().getFilesDir() [ " + requireContext().getFilesDir() + " ]");

                File file = new File(requireContext().getFilesDir(), fmt.format(new Date()) + "_notification.txt");
                FileWriter fileWriter = new FileWriter(file);

                // Write json to disk.
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                Log.d("log", "result.toString() -> " + getResult());
                bufferedWriter.write(getResult());
                bufferedWriter.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

            @SuppressLint("SimpleDateFormat") SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

            (new Thread(new WriteRunnable(result))).start();

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}