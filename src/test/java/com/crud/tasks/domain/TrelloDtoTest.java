package com.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Test;

public class TrelloDtoTest {

    @Test
    public void testTrelloDtoGetter(){
        //Given
        TrelloDto trelloDto = new TrelloDto(3,1);
        //When
        int board = trelloDto.getBoard();
        int card = trelloDto.getCard();
        //Then
        Assert.assertEquals(3, board);
        Assert.assertEquals(1, card);

    }
}
