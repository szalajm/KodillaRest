package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
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
import java.util.stream.Collectors;

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
                .queryParam("lists", "all").build().encode().toUri();

        TrelloBoardDto[] boardResponse = restTemplate.getForObject(uri, TrelloBoardDto[].class);

        System.out.println(uri);
        System.out.println(boardResponse);

        List<TrelloBoardDto> boardResponseList = Arrays.asList(boardResponse);
        List<Optional<TrelloBoardDto>>  optionalList =
                 boardResponseList.stream()
                .map(Optional::ofNullable)
                .collect(Collectors.toList());

        return Arrays.asList(Optional.ofNullable(boardResponse).orElse(new TrelloBoardDto[0]));
        //if (boardResponse != null) {
          //  return Arrays.asList(boardResponse);
        //}
        //return new ArrayList<>();
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){

        URI uri = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "cards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListsId()).build().encode().toUri();

        System.out.println(uri);
        return restTemplate.postForObject(uri, null, CreatedTrelloCard.class);
    }
}
