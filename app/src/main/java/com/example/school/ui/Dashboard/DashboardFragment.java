package com.example.school.ui.Dashboard;

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
import com.example.school.databinding.FragmentDashboardBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DashboardFragment extends Fragment {



    private DashboardViewModel dashboardViewModel;
        private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {


        FloatingActionButton Chat;



        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

    binding = FragmentDashboardBinding.inflate(inflater, container, false);
    View root = binding.getRoot();


    Chat=binding.fab;


    Chat.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ChatFragment chatFragment=new ChatFragment();
         FragmentTransaction transaction=getFragmentManager().beginTransaction();
         transaction.replace(R.id.nav_host_fragment_content_main,chatFragment);
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