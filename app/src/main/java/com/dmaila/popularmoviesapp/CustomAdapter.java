package com.dmaila.popularmoviesapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<Movie> movieList;
    private Context context;

    public CustomAdapter(ArrayList<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_view_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
        Picasso.with(context).load(movieList.get(position).getPosterUrl()).into(holder.mMoviePoster);
        holder.mMovieTitle.setText(movieList.get(position).getOriginalTitle());
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mMoviePoster;
        TextView mMovieTitle;

        public ViewHolder(View view) {
            super(view);
            mMoviePoster = (ImageView) view.findViewById(R.id.movie_poster_imageview);
            mMovieTitle = (TextView) view.findViewById(R.id.movie_title_textview);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            String title = movieList.get(position).getOriginalTitle();

            Toast.makeText(context, title, Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(context, MovieDetailActivity.class);
            context.startActivity(intent);


        }
    }
}
