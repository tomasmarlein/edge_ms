package fact.it.edgems.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Watchlist {
    @JsonIgnore
    private int id;
    private String uuid;
    private String movieUuid;
    private boolean isWatched;

    public Watchlist() {
    }

    public Watchlist(String uuid, String movieUuid, boolean isWatched) {
        this.uuid = uuid;
        this.movieUuid = movieUuid;
        this.isWatched = isWatched;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMovieUuid() {
        return movieUuid;
    }

    public void setMovieUuid(String movieUuid) {
        this.movieUuid = movieUuid;
    }

    public boolean isWatched() {
        return isWatched;
    }

    public void setWatched(boolean watched) {
        isWatched = watched;
    }
}