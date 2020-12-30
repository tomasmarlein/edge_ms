package fact.it.edgems.model;

public class Genre {
    private Integer id;
    private Integer uuid;
    private Integer movieId;
    private String name;

    public Genre() {
    }

    public Genre(Integer uuid, Integer movieId, String name) {
        this.uuid = uuid;
        this.movieId = movieId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}