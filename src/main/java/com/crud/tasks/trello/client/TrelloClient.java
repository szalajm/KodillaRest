package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.username}")
    private String trelloUsername;

    @Autowired
    private RestTemplate restTemplate;


    //private URI uriBuilder(){
        //return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "members/" + trelloUsername +"/boards")
          //      .queryParam("key", trelloAppKey)
            //    .queryParam("token", trelloToken)
              //  .queryParam("fields", "name,id").build().encode().toUri();
    //}

    public List<TrelloBoardDto> getTrelloBoards() {

        URI uri = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "members/" + trelloUsername + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .queryParam("Lists", "all").build().encode().toUri();

        TrelloBoardDto[] boardResponse = restTemplate.getForObject(uri, TrelloBoardDto[].class);

        System.out.println(uri);
        System.out.println(boardResponse);

        if (boardResponse != null) {
            return Arrays.asList(boardResponse);
        }
        return new ArrayList<>();
    }
}
