package fact.it.edgems.controller;

import fact.it.edgems.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
    public List<Review> getAllReviews(){

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
    public Review addReview(@RequestParam String movieUuid, @RequestParam String text, @RequestParam double rating){
        String randomUuid = getRandomUuid();
        Date date = new Date();

        return restTemplate.postForObject("http://" + reviewmsBaseUrl + "/reviews",
                new Review(randomUuid, movieUuid,text,rating, date),Review.class);
    }

    public String getRandomUuid(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }


}
