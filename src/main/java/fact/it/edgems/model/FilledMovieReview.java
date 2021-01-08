package fact.it.edgems.model;

import java.util.ArrayList;
import java.util.List;

public class FilledMovieReview {
    private String movieTitle;
    private List<Review> reviews;

    public FilledMovieReview(Movie movie, List<Review> reviews) {
        setMovieTitle(movie.getTitle());
        reviews = new ArrayList<>();
        reviews.forEach(review -> {

        });

    }

}
