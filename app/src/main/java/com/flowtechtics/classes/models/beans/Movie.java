package com.flowtechtics.classes.models.beans;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Movie {

    private String title;
    private String poster_path;
    private String original_title;
    private int id;
    private String backdrop_path;
}
