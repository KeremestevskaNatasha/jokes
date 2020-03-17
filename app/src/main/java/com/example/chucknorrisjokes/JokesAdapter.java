package com.example.chucknorrisjokes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chucknorrisjokes.model.Joke;

import java.util.ArrayList;
import java.util.List;

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokesViewHolder> {

    private List<Joke> jokes;
    private Context context;

    public JokesAdapter(Context context, List<Joke> value) {
        this.context = context;
        jokes = value;

    }
    @NonNull
    @Override
    public JokesAdapter.JokesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new JokesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JokesAdapter.JokesViewHolder holder, int position) {

        int adapterPosition = holder.getAdapterPosition();
        final Joke currentJoke = jokes.get(adapterPosition);

        holder.textViewNumbers.setText("Joke #" + adapterPosition);
        holder.textViewJokes.setText(currentJoke.getJoke());
        if (currentJoke.getCategories().size() != 0) {
            holder.textViewCategory.setText(currentJoke.getCategories().get(0));
        } else {
            holder.textViewCategory.setText("this list from categories is empty");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "joke");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, currentJoke.getJoke());
                context.startActivity(Intent.createChooser(sharingIntent, "Share via"));


            }
        });


    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }

    public class JokesViewHolder extends RecyclerView.ViewHolder {


        TextView textViewNumbers;
        TextView textViewJokes;
        TextView textViewCategory;

        public JokesViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNumbers = itemView.findViewById(R.id.textViewNumbers);
            textViewJokes = itemView.findViewById(R.id.textViewJokes);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
        }
    }
}
