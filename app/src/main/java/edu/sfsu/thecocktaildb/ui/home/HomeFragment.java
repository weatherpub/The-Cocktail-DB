package edu.sfsu.thecocktaildb.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import edu.sfsu.thecocktaildb.Model.CocktailModel;
import edu.sfsu.thecocktaildb.R;
import edu.sfsu.thecocktaildb.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    /**
     * Note: onCreateView acts like a constructor.
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    MutableLiveData<CocktailModel> liveData;   // HomeViewModel homeViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        final Observer<CocktailModel> cocktailObserver = new Observer<CocktailModel>() {
            @Override
            public void onChanged(CocktailModel cocktailModels) {
                Log.i("log", "onChanged" + cocktailModels.getStrDrink());
            }
        };

        TextView tv_strDrink = view.findViewById(R.id.text_strDrink);

        homeViewModel.getLiveData().observe(getViewLifecycleOwner(), data-> {
            tv_strDrink.setText(data.getStrDrink());
        });

        homeViewModel.getLiveData().observe(getViewLifecycleOwner(), cocktailObserver);

        return view;
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}