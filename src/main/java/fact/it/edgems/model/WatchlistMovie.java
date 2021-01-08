package fact.it.edgems.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class WatchlistMovie {
    private Movie movie;
    private Watchlist watchlist;

    public WatchlistMovie() {
    }

    public WatchlistMovie(Movie movie, Watchlist watchlist) {
        this.movie = movie;
        this.watchlist = watchlist;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Watchlist watchlist) {
        this.watchlist = watchlist;
    }
}
