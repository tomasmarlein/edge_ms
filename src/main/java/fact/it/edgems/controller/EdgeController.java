package fact.it.edgems.controller;

import fact.it.edgems.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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

        List<Review> returnList= new ArrayList();

        ResponseEntity<List<Review>> responseEntityReviews =
                restTemplate.exchange("http://" + reviewmsBaseUrl + "/reviews/all",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {
                        });

        List<Review> reviews = responseEntityReviews.getBody();

        return returnList;
    }


}
