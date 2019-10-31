package com.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Test;

public class TrelloCardDtoTest {

    @Test
    public void testNoArgsConstructor(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto();

        //When
        String description = trelloCardDto.getDescription();

        //Then
        Assert.assertEquals(null, description);
    }
}
