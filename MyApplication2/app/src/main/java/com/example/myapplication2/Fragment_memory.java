package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_memory extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mem_frag = inflater.inflate(R.layout.memory_fragment, container, false);
        Button button = mem_frag.findViewById(R.id.mem_test);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this.getContext(), Memory_test.class);
            startActivity(intent);
        });
        return mem_frag;
    }
}
