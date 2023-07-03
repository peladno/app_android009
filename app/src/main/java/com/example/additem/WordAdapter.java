package com.example.additem;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private List<String> wordList;
    private OnItemClickListener onItemClickListener;


    public WordAdapter(List<String> wordList) {
        this.wordList = wordList;
    }


    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String word = wordList.get(position);
        holder.tvWord.setText(word);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position, word);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public void addWord(String word) {
        wordList.add(word);
        notifyDataSetChanged();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {

        TextView tvWord;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWord = itemView.findViewById(R.id.tvWord);
        }
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(int position, String word);
    }


}
