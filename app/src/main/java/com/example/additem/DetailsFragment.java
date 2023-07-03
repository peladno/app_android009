package com.example.additem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.additem.databinding.FragmentDetailsBinding;

public class DetailsFragment extends Fragment {

    private static final int ARG_POSITION = 0;
    private static final String ARG_WORD = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;


    private FragmentDetailsBinding detailsBinding;
    private OnWordModifiedListener wordModifiedListener;


    public void setOnWordModifiedListener(OnWordModifiedListener listener) {
        this.wordModifiedListener = listener;
    }

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(int position, String word) {
        DetailsFragment fragment = new DetailsFragment();

        Bundle args = new Bundle();
        args.putInt(String.valueOf(ARG_POSITION), position);
        args.putString(ARG_WORD, word);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(String.valueOf(ARG_POSITION));
            mParam2 = getArguments().getString(ARG_WORD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        detailsBinding = FragmentDetailsBinding.inflate(inflater, container, false);
        View view = detailsBinding.getRoot();

        TextView textView = detailsBinding.viewWord;
        EditText editText = detailsBinding.etWord;
        Button button = detailsBinding.btnSave;

        textView.setText(mParam2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modifiedWord = editText.getText().toString();
                if (wordModifiedListener != null) {
                    wordModifiedListener.onWordModified(mParam1, modifiedWord);
                }
                requireActivity().onBackPressed();
            }
        });

        return view;
    }

    public interface OnWordModifiedListener {
        void onWordModified(int position, String modifiedWord);
    }

}