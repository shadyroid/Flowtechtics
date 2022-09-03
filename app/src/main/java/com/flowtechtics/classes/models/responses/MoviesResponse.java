package com.flowtechtics.classes.models.responses;

import com.flowtechtics.classes.models.beans.Movie;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MoviesResponse extends BaseResponse {


    private List<Movie> results;

}
