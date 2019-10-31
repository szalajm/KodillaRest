package com.crud.tasks.service;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
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
public class TrelloServiceTestSuite {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Test
    public void shouldFetchTrelloBoardDtos(){
        //Given
        TrelloListDto trelloListDtoOne = new TrelloListDto("trelloListDtoOne", "1", true);
        TrelloListDto trelloListDtoTwo = new TrelloListDto("trelloListDtoTwo", "2", false);
        List<TrelloListDto> listsDto = new ArrayList<>();
        listsDto.add(trelloListDtoOne);
        listsDto.add(trelloListDtoTwo);

        TrelloBoardDto trelloBoardDtoOne = new TrelloBoardDto("trelloBoardDtoOne", "1", listsDto);
        TrelloBoardDto trelloBoardDtoTwo = new TrelloBoardDto("trelloBoardDtoTwo", "2", listsDto);

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(trelloBoardDtoOne);
        trelloBoardsDto.add(trelloBoardDtoTwo);

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardsDto);

        //When
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();

        //Then
        Assert.assertEquals(2,result.size());
    }

}
