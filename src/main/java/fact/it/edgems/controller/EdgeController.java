package fact.it.edgems.controller;

import fact.it.edgems.model.Review;
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
    public Review updateReview(@RequestParam String uuid, @RequestParam String movieUuid, @RequestParam String text, @RequestParam double rating, @RequestParam Date date) {
        Review review = new Review(uuid, movieUuid, text, rating, date);

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


}
