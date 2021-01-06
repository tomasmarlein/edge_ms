package fact.it.edgems.controller;

import fact.it.edgems.model.Genre;
import fact.it.edgems.model.Movie;
import fact.it.edgems.model.Review;
import fact.it.edgems.model.Watchlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
public class EdgeController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${reviewms.baseurl}")
    private String reviewmsBaseUrl;

    @Value("${moviems.baseurl}")
    private String moviemsBaseUrl;

    @Value("${watchlistms.baseurl}")
    private String watchlistmsBaseUrl;

    @Value("${genrems.baseurl}")
    private String genremsBaseUrl;

    @GetMapping("/reviews/all")
    public List<Review> getAllReviews() {

        ResponseEntity<List<Review>> responseEntityReviews =
                restTemplate.exchange("http://" + reviewmsBaseUrl + "/reviews/all",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
                        });

        return responseEntityReviews.getBody();
    }

//    getMovieWithReviews
//    gebruikt: getReviewsByMovieUuid
//    URL: /reviews/movie/{movieUuid}


    @PostMapping("/reviews")
    public Review addReview(@RequestParam String movieUuid, @RequestParam String text, @RequestParam double rating) {
        String randomUuid = getRandomUuid();
        Date date = new Date();
        return restTemplate.postForObject("http://" + reviewmsBaseUrl + "/reviews",
                new Review(randomUuid, movieUuid, text, rating, date), Review.class);
    }

    public String getRandomUuid() {
        return UUID.randomUUID().toString();
    }

    @PutMapping("/reviews")
    public Review updateReview(@RequestParam String uuid, @RequestParam String movieUuid, @RequestParam String text, @RequestParam double rating) {
        Review review = new Review(uuid, movieUuid, text, rating, new Date());

        ResponseEntity<Review> responseEntityReview =
                restTemplate.exchange("http://" + reviewmsBaseUrl + "/reviews",
                        HttpMethod.PUT, new HttpEntity<>(review), Review.class);

        return responseEntityReview.getBody();
    }

    @DeleteMapping("/reviews/{uuid}")
    public ResponseEntity deleteReview(@PathVariable String uuid){

        restTemplate.delete("http://" + reviewmsBaseUrl + "/reviews/" + uuid);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/watchlist/all")
    public List<Watchlist> getAll(){
        ResponseEntity<List<Watchlist>> responseEntityWatchlist =
                restTemplate.exchange("http://" + watchlistmsBaseUrl + "/watchlist/all",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Watchlist>>() {
                        });

        return responseEntityWatchlist.getBody();
    }

    @GetMapping("/watchlist/{uuid}")
    public Watchlist getOne(@PathVariable UUID uuid){
        ResponseEntity<Watchlist> responseEntityWatchlist =
                restTemplate.exchange("http://" + watchlistmsBaseUrl + "/watchlist/" +uuid.toString(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<Watchlist>() {
                        });

        return responseEntityWatchlist.getBody();
    }

    @GetMapping("/watchlist/watched")
    public List<Watchlist> getWatchlistWatched(){
        ResponseEntity<List<Watchlist>> responseEntityWatchlist =
                restTemplate.exchange("http://" + watchlistmsBaseUrl + "/watchlist/watched",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Watchlist>>() {
                        });

        return responseEntityWatchlist.getBody();
    }

    @PostMapping("/watchlist/{movieUuid}")
    public Watchlist add(@PathVariable UUID movieUuid){
        ResponseEntity<Watchlist> responseEntityWatchlist =
                restTemplate.exchange("http://" + watchlistmsBaseUrl + "/watchlist/"+movieUuid,
                        HttpMethod.POST, null, new ParameterizedTypeReference<Watchlist>() {
                        });

        return responseEntityWatchlist.getBody();
    }

    @PutMapping("/watchlist/watched/{uuid}")
    public Watchlist setWatched(@PathVariable UUID uuid){
        ResponseEntity<Watchlist> responseEntityWatchlist =
                restTemplate.exchange("http://" + watchlistmsBaseUrl + "/watchlist/watched/"+uuid,
                        HttpMethod.PUT, null, new ParameterizedTypeReference<Watchlist>() {
                        });

        return responseEntityWatchlist.getBody();
    }

    @DeleteMapping("/watchlist/{movieUuid}")
    public ResponseEntity delete(@PathVariable UUID movieUuid){
        restTemplate.delete("http://" + watchlistmsBaseUrl + "/watchlist/" + movieUuid);

        return ResponseEntity.ok().build();
    }

    /* Movie endpoints */
    @GetMapping("/movie/all")
    public List<Movie> getAllMovies(){
        ResponseEntity<List<Movie>> responseEntityMovie =
                restTemplate.exchange("http://" + moviemsBaseUrl + "/movie/all",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Movie>>() {
                        });

        return responseEntityMovie.getBody();
    }

    @GetMapping("/movie/{uuid}")
    public Movie getOneMovie(@PathVariable UUID uuid){
        ResponseEntity<Movie> responseEntityMovie =
                restTemplate.exchange("http://" + moviemsBaseUrl + "/movie/" +uuid.toString(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<Movie>() {
                        });
        return responseEntityMovie.getBody();
    }


    /* Genre endpoints */
    @GetMapping("/genres/all")
    public List<Genre> getAllGenres(){
        ResponseEntity<List<Genre>> responseEntityGenre =
                restTemplate.exchange("http://" + genremsBaseUrl + "/genres/all",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Genre>>() {
                        });

        return responseEntityGenre.getBody();
    }

    @GetMapping("/genres/{uuid}")
    public List<Genre> getGenresByUuid(@PathVariable String uuid){
        ResponseEntity<List<Genre>> responseEntityGenre =
                restTemplate.exchange("http://" + genremsBaseUrl + "/genres/" + uuid,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Genre>>() {
                        });
        return responseEntityGenre.getBody();
    }

}
