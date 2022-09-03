package com.flowtechtics.classes.dialogs;

import static com.flowtechtics.classes.others.CONSTANTS.BACKEND.STORAGE_URL;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.flowtechtics.R;
import com.flowtechtics.classes.models.beans.Movie;
import com.flowtechtics.databinding.DialogMovieDetailsBinding;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ActivityContext;


public class MovieDetailsDialog extends Dialog {
    protected final Animation mModalInAnim;
    protected final Animation mModalOutAnim;
    protected View body;

    DialogMovieDetailsBinding mBinding;
    Context context;

    @Inject
    public MovieDetailsDialog(@ActivityContext Context context) {
        super(context);
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mBinding = DialogMovieDetailsBinding.inflate(LayoutInflater.from(context));

        setContentView(mBinding.getRoot());
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 32);

        getWindow().setBackgroundDrawable(inset);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mModalInAnim = AnimationUtils.loadAnimation(context, R.anim.fade_in);

        mModalOutAnim = AnimationUtils.loadAnimation(context, R.anim.fade_out);
        mModalOutAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                body.post(() -> dismiss());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        setCancelable(false);

        setOnKeyListener(this::onKey);
    }

    protected boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            dismissDialog();
        }
        return false;
    }

    @Override
    public void setContentView(@NonNull View view) {
        super.setContentView(view);
        body = view;
    }

    public void showDialog() {
        if (!isShowing()) {
            show();
            body.startAnimation(mModalInAnim);
        }

    }

    public void dismissDialog() {
        if (isShowing()) {
            body.startAnimation(mModalOutAnim);
        }


    }

    public void setMovie(Movie movie) {
        Glide.with(context)
                .load(STORAGE_URL + movie.getPoster_path())
                .placeholder(R.drawable.logo_placeholder)
                .error(R.drawable.logo_placeholder)
                .into(mBinding.ivPoster);
        mBinding.tvTitle.setText(movie.getTitle());
    }


}
