package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
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

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);

        //Then
        Assert.assertEquals(2, trelloBoards.size());
    }

    @Test
    public void testMapToBoardsDto(){
        //Given
        TrelloList trelloListOne = new TrelloList("trelloListOne", "1", false);
        TrelloList trelloListTwo = new TrelloList("trelloListTwo", "2", true);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloListOne);
        lists.add(trelloListTwo);

        TrelloBoard trelloBoardOne = new TrelloBoard("trelloBoardOne", "1", lists);
        TrelloBoard trelloBoardTwo = new TrelloBoard("trelloBoardTwo", "2", lists);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoardOne);
        trelloBoards.add(trelloBoardTwo);

        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        Assert.assertEquals("trelloBoardOne", trelloBoardsDto.get(0).getName());

    }

    @Test
    public void testMapToList(){
        //Given
        TrelloListDto trelloListDtoOne = new TrelloListDto("trelloListDtoOne", "1", false);
        TrelloListDto trelloListDtoTwo = new TrelloListDto("trelloListDtoTwo", "2", true);
        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(trelloListDtoOne);
        lists.add(trelloListDtoTwo);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(lists);

        //Then
        Assert.assertTrue(trelloLists.get(1).isClosed());
    }

    @Test
    public void testMapToListDto(){
        //Given
        TrelloList trelloListOne = new TrelloList("trelloListOne", "1", true);
        TrelloList trelloListTwo = new TrelloList("trelloListTwo", "2", false);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(trelloListOne);
        lists.add(trelloListTwo);

        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(lists);

        //Then
        Assert.assertEquals(2, trelloListsDto.size());
    }

    @Test
    public void testMapToCardDto(){
        //Given
        TrelloCard trelloCard = new TrelloCard("card", "testing card",
                "test", "trelloListOne");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //When
        Assert.assertTrue(trelloCardDto.getListId().contains("One"));
    }

    @Test
    public void testMapToCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card", "testing card",
                "test", "trelloListTwo");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //When
        Assert.assertTrue(trelloCard.getListId().contains("Two"));
    }
}
