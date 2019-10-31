package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.fasade.TrelloFacade;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloControllerTestSuite {

    @InjectMocks
    private TrelloController trelloController;

    @Mock
    private TrelloFacade trelloFacade;

    @Test
    public void shouldFetchTrelloBoards(){
        //Given
        TrelloListDto listOne = new TrelloListDto("1", "test_list", false);
        TrelloListDto listTwo = new TrelloListDto("2", "test_list", true);
        List<TrelloListDto> lists =  new ArrayList<>();
        lists.add(listOne);
        lists.add(listTwo);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "test_board", lists);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto);

        when(trelloFacade.fetchTrelloBoard()).thenReturn(trelloBoardDtos);

        //when
        List<TrelloBoardDto> result = trelloController.getTrelloBoards();

        //Then
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void shouldCreateTrelloCard(){
        //Given
        TrelloCardDto trelloCardDto =  new TrelloCardDto("test_card","test", "post", "none");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1","test_card", "test_url");
        when(trelloFacade.createCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto result = trelloController.createTrelloCard(trelloCardDto);

        //Then
        Assert.assertEquals("test_card", result.getName());
    }
}
