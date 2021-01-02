package fact.it.edgems.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class Movie {
    @JsonIgnore
    private int id;
    private String uuid, title, originalTitle;
    private int year, runtimeMinutes;
    private double rating;

    public Movie(){}

    public int getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public int getYear() {
        return year;
    }

    public int getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public double getRating() {
        return rating;
    }

}
