package fact.it.edgems.model;

import java.util.List;

public class FilledMovieReview {
    private Movie movie;
    private List<Review> reviews;

    public FilledMovieReview() {
    }

    public FilledMovieReview(Movie movie, List<Review> reviews) {
        this.movie = movie;
        this.reviews = reviews;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
