package com.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TrelloBoardDtoTest {

    @Test
    public void testTrelloBoardsDtoGetter() {
        //Given
        TrelloListDto trelloListDtoOne = new TrelloListDto("1", "test_list_one", false);
        TrelloListDto trelloListDtoTwo = new TrelloListDto("2", "test_list_two", true);
        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(trelloListDtoOne);
        lists.add(trelloListDtoTwo);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("test_board", "1", lists);

        //When
        String nameOne = trelloBoardDto.getLists().get(0).getName();

        //Then
        Assert.assertEquals("test_list_one", nameOne);

    }
}
