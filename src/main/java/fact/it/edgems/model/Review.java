package fact.it.edgems.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class Review {
    @JsonIgnore
    private String id;
    private String uuid;
    private String movieUuid;
    private String text;
    private double rating;
    private Date date;

    public Review() {
    }

    public Review(String uuid, String movieUuid, String text, double rating, Date date) {
        this.uuid = uuid;
        this.movieUuid = movieUuid;
        this.text = text;
        this.rating = rating;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
