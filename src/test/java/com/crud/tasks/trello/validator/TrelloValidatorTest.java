package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloValidatorTest {

    @Autowired
    TrelloValidator trelloValidator;

    @Test
    void shouldValidateCard() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name", "description", "pos", "listId");
        //When & Then
        trelloValidator.validateCard(trelloCard);
    }

    @Test
    void shouldValidateCardAsTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test card", "description", "pos", "listId");
        //When & Then
        trelloValidator.validateCard(trelloCard);
    }

    @Test
    void shouldValidateTrelloBoards() {
        //Given
        TrelloBoard trelloBoard = new TrelloBoard("id", "name", new ArrayList<>());
        TrelloBoard testTrelloBoard = new TrelloBoard("test id", "test", new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);
        trelloBoards.add(testTrelloBoard);
        //When
        List<TrelloBoard> validatedTrelloBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertNotNull(validatedTrelloBoards);
        assertEquals(1, validatedTrelloBoards.size());
    }

    @Test
    void shouldReturnEmptyList() {
        //Given
        List<TrelloBoard> trelloBoards = List.of(
                new TrelloBoard("test id", "test", new ArrayList<>()),
                new TrelloBoard("test id2", "test", new ArrayList<>())
        );
        //When
        List<TrelloBoard> validatedTrelloBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertNotNull(validatedTrelloBoards);
        assertTrue(validatedTrelloBoards.isEmpty());
    }
}