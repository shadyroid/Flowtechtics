package com.flowtechtics.classes.adapters;

import static com.flowtechtics.classes.others.CONSTANTS.BACKEND.STORAGE_URL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.flowtechtics.R;
import com.flowtechtics.classes.models.beans.Movie;
import com.flowtechtics.databinding.ListItemMovieBinding;

import java.util.List;

import javax.inject.Inject;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    //The list of Objects that will populate the RecyclerView
    private List<Movie> mData;

    Context context;

    // ClickHandler Build
    private MoviesAdapterOnClickHandler mClickHandler;


    @Inject
    public MoviesAdapter() {

    }

    public void setItemClickListener(MoviesAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;

    }

    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false);
        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterViewHolder holder, int position) {
        Glide.with(context)
                .load(STORAGE_URL+mData.get(position).getPoster_path())
                .placeholder(R.drawable.logo_placeholder)
                .error(R.drawable.logo_placeholder)
                .into(holder.mBinding.ivPoster);
        holder.mBinding.tvTitle.setText(mData.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        if (null == mData) return 0;
        return mData.size();
    }

    public void setData(List<Movie> data) {
        if (mData == null)
            mData = data;
        else
            mData.addAll(data);

        notifyDataSetChanged();
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    //The interface that will be implemented later in parent activity
    public interface MoviesAdapterOnClickHandler {
        void onMovieItemClick(Movie notification);

    }

    public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder {

        //get an Instance for dataBinding
        ListItemMovieBinding mBinding;


        public MoviesAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mBinding = ListItemMovieBinding.bind(itemView);
            itemView.setOnClickListener(this::onItemClick);
        }

        public void onItemClick(View view) {
            int adapterPosition = getAdapterPosition();
            //Data passed when clicked
            Movie notification = mData.get(adapterPosition);
            if (mClickHandler != null)
                mClickHandler.onMovieItemClick(notification);

        }

    }


}
