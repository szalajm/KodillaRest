package com.crud.tasks.trello.fasade;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.service.TrelloService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TrelloFacade {

    @Autowired
    private TrelloService trelloService;

    public List<TrelloBoardDto> fetchTrelloBoard(){
        List<TrelloBoardDto> trelloBoards = trelloService.fetchTrelloBoards();

    }
}
