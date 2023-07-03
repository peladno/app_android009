package com.example.additem;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomFragment extends Fragment implements WordAdapter.OnItemClickListener, DetailsFragment.OnWordModifiedListener {


    private RecyclerView recyclerView;
    private Button btnAdd;
    private WordAdapter wordAdapter;
    private List<String> wordList;

    private static final String KEY_WORD_LIST = "wordList";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hom, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        btnAdd = view.findViewById(R.id.btnAdd);

        wordList = new ArrayList<>();
        wordAdapter = new WordAdapter(wordList);

        recyclerView.setAdapter(wordAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWord();
            }
        });

        wordAdapter.setOnItemClickListener(this);

    }

    private void addWord() {
        String newWord = "New Word";
        wordAdapter.addWord(newWord);
    }

    @Override
    public void onItemClick(int position, String word) {
        // Abrir el DetailsFragment y pasar la posición y la palabra seleccionada como argumentos
        DetailsFragment detailFragment = DetailsFragment.newInstance(position, word);
        detailFragment.setOnWordModifiedListener(this);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onWordModified(int position, String modifiedWord) {
        // Modificar la palabra en la lista de palabras en la posición proporcionada
        wordList.set(position, modifiedWord);
        // Notificar al adaptador del cambio
        wordAdapter.notifyItemChanged(position);
    }


}
