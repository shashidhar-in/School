package com.example.school.ui;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.school.ChatFragment;
import com.example.school.R;
import com.example.school.databinding.FragmentSDashboardBinding;
import com.example.school.ui.Dashboard.DashboardViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class S_DashboardFragment extends Fragment {


    private DashboardViewModel dashboardViewModel;
    private FragmentSDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        FloatingActionButton Chat;


        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentSDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        Chat = binding.fab;


        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatFragment chatFragment = new ChatFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment_content_main_student, chatFragment);
                transaction.commit();


            }
        });


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
