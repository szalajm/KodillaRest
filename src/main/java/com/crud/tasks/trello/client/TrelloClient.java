package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.slf4j.LoggerFactory;


@Component
public class TrelloClient {

    @Autowired
    private TrelloConfig trelloConfig;

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);


    //private URI uriBuilder(){
        //return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "members/" + trelloUsername +"/boards")
          //      .queryParam("key", trelloAppKey)
            //    .queryParam("token", trelloToken)
              //  .queryParam("fields", "name,id").build().encode().toUri();
    //}

    public List<TrelloBoardDto> getTrelloBoards() {

        URI uri = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "members/" + trelloConfig.getTrelloUsername() + "/boards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all").build().encode().toUri();

        try {
            TrelloBoardDto[] boardResponse = restTemplate.getForObject(uri, TrelloBoardDto[].class);
            return Arrays.asList(Optional.ofNullable(boardResponse).orElse(new TrelloBoardDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){

        URI uri = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token",trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId()).build().encode().toUri();

        System.out.println(uri);
        return restTemplate.postForObject(uri, null, CreatedTrelloCard.class);
    }
}
