package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.fasade.TrelloFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;




import java.util.ArrayList;
import java.util.List;

import static javax.swing.UIManager.get;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@RunWith(SpringRunner.class)
@WebMvcTest(TrelloController.class)
public class TrelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrelloFacade trelloFacade;

    @Test
    public void shouldFetchEmptyTrelloBoards() throws Exception{
        //Given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        when(trelloFacade.fetchTrelloBoard()).thenReturn(trelloBoards);

        //When&Then
        mockMvc.perform(get("/v1/trello/getTrelloBoards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) //or isOK()
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void shouldFetchTrelloBoards() throws Exception{
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "Test List", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "Test Task", trelloLists));

        when(trelloFacade.fetchTrelloBoard()).thenReturn(trelloBoards);

        //When&Then


    }
}
