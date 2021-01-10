package fact.it.edgems;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.edgems.model.Genre;
import fact.it.edgems.model.Movie;
import fact.it.edgems.model.Review;
import fact.it.edgems.model.Watchlist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EdgemsControllerUnitTests {

    @Value("${reviewms.baseurl}")
    private String reviewmsBaseUrl;

    @Value("${moviems.baseurl}")
    private String moviemsBaseUrl;

    @Value("${watchlistms.baseurl}")
    private String watchlistmsBaseUrl;

    @Value("${genrems.baseurl}")
    private String genremsBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    private Movie movie1 = new Movie("Testmovie 1", "Testfilm 1", 2020, 61, 8.1 );
    private Movie movie2 = new Movie("Testmovie 2", "Testfilm 2", 2020, 62, 8.2 );
    private Movie movie3 = new Movie("Testmovie 3", "Testfilm 3", 2020, 63, 8.3 );

    private Review review1Movie1 = new Review("1", movie1.getUuid(), "review 1 movie 1", 7.25, new Date());
    private Review review2Movie1 = new Review("2", movie1.getUuid(), "review 2 movie 1", 2, new Date());

    private Watchlist watchlist = new Watchlist("21d14364-8e94-41a3-824a-a44df76d59d8", movie1.getUuid(), false);

    private Genre genre = new Genre("964df97f-2cd4-4e1a-acf9-c21b2ad1e947", "genre 1");

    private List<Review> allReviewsFromMovie1 = Arrays.asList(review1Movie1, review2Movie1);
    private List<Watchlist> allWatchlist = Arrays.asList(watchlist);


    @BeforeEach
    public void initializeMockserver() {
        mockServer = MockRestServiceServer.createServer(restTemplate);

    }

    @Test
    public void whenGetMovieWithReviews_thenReturnFilledMovieReviewJson() throws Exception {

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + reviewmsBaseUrl + "/reviews/movie/" + movie1.getUuid())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(allReviewsFromMovie1))
                );

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + moviemsBaseUrl + "/movie/" + movie1.getUuid())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(movie1))
                );
    }

    @Test
    public void whenGetAllWatchlistWithMovie_thenReturnWatchlistMovieJson() throws Exception {

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + watchlistmsBaseUrl + "/watchlist/all/")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(allReviewsFromMovie1))
                );

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://" + moviemsBaseUrl + "/movie/" + watchlist.getMovieUuid())))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(movie1))
                );
    }















}
