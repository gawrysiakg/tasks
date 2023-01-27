package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;


    @Test
    void shouldMapToBoards(){
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("3", "listDto1", false));
        trelloListDto.add(new TrelloListDto("4", "listDto2", true));
        List<TrelloListDto> trelloListDto2 = new ArrayList<>();
        trelloListDto2.add(new TrelloListDto("5", "listDto3", true));

        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "first", trelloListDto);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "second", trelloListDto2);
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(trelloBoardDto1);
        trelloBoardDto.add(trelloBoardDto2);
        //When
        List<TrelloBoard> mappedToBoard = trelloMapper.mapToBoards(trelloBoardDto);
        //Then
        assertEquals("first", mappedToBoard.get(0).getName());
        assertEquals(2, mappedToBoard.size());
        assertEquals("listDto3", mappedToBoard.get(1).getLists().get(0).getName());
    }

    @Test
    public void shouldMapToBoardsDto() {
        //Given
        List<TrelloList> listTrelloList = new ArrayList<>();
        listTrelloList.add(new TrelloList("1", "list1", false));
        List<TrelloList> listTrelloList2 = new ArrayList<>();
        listTrelloList2.add(new TrelloList("2", "list2", true));
        TrelloBoard trelloBoard = new TrelloBoard("1", "first", listTrelloList);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "second", listTrelloList2);
        List<TrelloBoard> listTrelloBoard = new ArrayList<>();
        listTrelloBoard.add(trelloBoard);
        listTrelloBoard.add(trelloBoard2);
        //When
        List<TrelloBoardDto> mappedToBoardDto = trelloMapper.mapToBoardsDto(listTrelloBoard);
        //Then
        assertEquals("1", mappedToBoardDto.get(0).getId());
        assertEquals("first", mappedToBoardDto.get(0).getName());
        assertTrue(mappedToBoardDto.get(1).getLists().get(0).isClosed());
    }

    @Test
    public void shouldMapToList(){
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("3", "listDto1", false));
        trelloListDto.add(new TrelloListDto("4", "listDto2", true));
        //When
        List<TrelloList> mappedTrelloList = trelloMapper.mapToList(trelloListDto);
        //Then
        assertEquals(2, mappedTrelloList.size());
        assertEquals("listDto2", mappedTrelloList.get(1).getName());
        assertEquals("4", mappedTrelloList.get(1).getId());
    }

    @Test
    public void shouldMapToListDto(){
        //Given
        List<TrelloList> listTrelloList = new ArrayList<>();
        listTrelloList.add(new TrelloList("1", "list1", false));
        listTrelloList.add(new TrelloList("2", "list2", true));
        //When
        List<TrelloListDto> mappedToListDto = trelloMapper.mapToListDto(listTrelloList);
        //Then
        assertEquals(2, mappedToListDto.size());
        assertEquals("list2", mappedToListDto.get(1).getName());
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card1", "example description", "top", "2");
        //When
        TrelloCardDto mappedToCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("top", mappedToCardDto.getPos());
    }

    @Test
    public void shouldMapToCard(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card1", "example description", "top", "2");
        //When
        TrelloCard mappedToCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("card1", mappedToCard.getName());
    }

}

