package fact.it.edgems.controller;

import fact.it.edgems.model.Watchlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
public class WatchlistController {
    @Value("${watchlistms.baseurl}")
    private String watchlistmsBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

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

    @PostMapping("watchlist/{movieUuid}")
    public Watchlist add(@PathVariable UUID movieUuid){
        ResponseEntity<Watchlist> responseEntityWatchlist =
                restTemplate.exchange("http://" + watchlistmsBaseUrl + "/watchlist/"+movieUuid,
                        HttpMethod.POST, null, new ParameterizedTypeReference<Watchlist>() {
                        });

        return responseEntityWatchlist.getBody();
    }

    @PutMapping("watchlist/watched/{uuid}")
    public Watchlist setWatched(@PathVariable UUID uuid){
        ResponseEntity<Watchlist> responseEntityWatchlist =
                restTemplate.exchange("http://" + watchlistmsBaseUrl + "/watchlist/"+uuid,
                        HttpMethod.PUT, null, new ParameterizedTypeReference<Watchlist>() {
                        });

        return responseEntityWatchlist.getBody();
    }

    @DeleteMapping("watchlist/{movieUuid}")
    public ResponseEntity delete(@PathVariable UUID movieUuid){
        restTemplate.delete("http://" + watchlistmsBaseUrl + "/watchlist/" + movieUuid);

        return ResponseEntity.ok().build();
    }
}
