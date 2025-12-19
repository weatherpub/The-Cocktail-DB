package edu.sfsu.thecocktaildb.ui.dashboard;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.sfsu.thecocktaildb.Model.ArtistModel;
import edu.sfsu.thecocktaildb.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    // private MutableLiveData<ArrayList<ArtistModel>> liveData;
    private LiveData<ArrayList<ArtistModel>> liveData;
    private ArrayList<ArtistModel> model;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        DashboardViewModel viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        final TextView id = binding.tvIdArtist;
        final TextView artist = binding.tvStrArtist;
        final ImageView thumb = binding.ivArtistThumb;
        final TextView biography = binding.tvStrBiographyEN;
//        final TextView artistStripped = binding.tvStrArtistStripped;
//        final TextView artistAlternate = binding.tvStrArtistAlternate;
//        final TextView strLabel = binding.tvStrLabel;
//        final TextView idLabel = binding.tvIdLabel;
//        final TextView intFormedYear = binding.tvIntFormedYear;
//        final TextView intBornYear = binding.tvIntBornYear;
//        final TextView strGenre = binding.tvStrGenre;
//        final TextView strBiographyEN = binding.tvStrBiographyEN;
//        final TextView strBiographyDE = binding.tvStrBiographyDE;
//        final TextView strBiographyFR = binding.tvStrBiographyFR;

        final Observer<ArrayList<ArtistModel>> observer = new Observer<ArrayList<ArtistModel>>() {
            @Override
            public void onChanged(ArrayList<ArtistModel> m) {
                Picasso.get().load(m.get(0).getStrArtistThumb()).into(thumb);
                id.setText(m.get(0).getIdLabel());
                artist.setText(m.get(0).getStrArtist());
                biography.setText(m.get(0).getStrBiographyEN());
               // binding.rvDashFragment.setAdapter(new DashboardRVAdapter());
               // binding.rvDashFragment.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        };

        viewModel.getLiveData().observe(getViewLifecycleOwner(), observer);
        //liveData = viewModel.getLiveData();
        Log.i("log", "This is the model that I think I passed in: " + model);
            /*
        dashboardViewModel.getLiveData().observe(getViewLifecycleOwner(), data -> {
            binding.rvDashFragment.setAdapter(new DashboardRVAdapter());
            binding.rvDashFragment.setLayoutManager(new LinearLayoutManager(getContext()));
            id.setText(data.get(0).getIdArtist());
            artist.setText(data.get(0).getStrArtist());
            artistStripped.setText(data.get(0).getStrArtistStripped());
            artistAlternate.setText(data.get(0).getStrArtistAlternate());
            strLabel.setText(data.get(0).getStrLabel());
            idLabel.setText(data.get(0).getIdLabel());
            intFormedYear.setText(data.get(0).getIntFormedYear());
            intBornYear.setText(data.get(0).getIntBornYear());
            strGenre.setText(data.get(0).getStrGenre());
            strBiographyEN.setText(data.get(0).getStrBiographyEN());
            strBiographyDE.setText(data.get(0).getStrBiographyDE());
            strBiographyFR.setText(data.get(0).getStrBiographyFR());
        });
            */

        //dashboardViewModel.getLiveData().getValue().get(0).getIntBornYear();

        /*
        @SuppressLint("SimpleDateFormat") SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        final Handler handler = new Handler();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    File file = new File(requireContext().getFilesDir(), fmt.format(new Date()) + "_home.txt");
                    FileWriter fileWriter = new FileWriter(file);

                    // Write JSON response to disk.
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(result.toString());
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
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        */

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}