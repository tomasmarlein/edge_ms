package fact.it.edgems.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Genre {
    @JsonIgnore
    private int id;
    private String uuid;
    private String name;

    public Genre() {
    }

    public Genre(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}