package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;


    @Test
    void shouldReturnEmptyList() {
        //Given & When
        List<Task> tasks = dbService.getAllTasks();
        //Then
        assertNotNull(tasks);
        assertEquals(0, tasks.size());
    }

    @Test
    void shouldGetAllTasks() {
        //Given
        List<Task> tasksMock = List.of(new Task(1L, "title", "content"));
        when(taskRepository.findAll()).thenReturn(tasksMock);
        //When
        List<Task> tasks = dbService.getAllTasks();
        //Then
        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals("title", tasks.get(0).getTitle());
        assertEquals("content", tasks.get(0).getContent());
    }

    @Test
    void shouldThrowException() {
        //Given & When
        //Then
        assertThrows(TaskNotFoundException.class, ()->dbService.getTask(1L ));
    }

    @Test
    void shouldGetTask() throws TaskNotFoundException {
        //Given
        Task mockedTask = new Task(1L, "title", "content");
        when(taskRepository.findById(mockedTask.getId())).thenReturn(Optional.of(mockedTask));
        //When
        Task task = dbService.getTask(mockedTask.getId());
        //Then
        assertNotNull(task);
        assertEquals("title", task.getTitle());
        assertEquals("content", task.getContent());
    }

    @Test
    void shouldSaveTask() {
        //Given
        Task taskMock = new Task(1L, "title", "content");
        when(taskRepository.save(taskMock)).thenReturn(taskMock);
        //When
        Task savedTask = dbService.saveTask(taskMock);
        //Then
        assertNotNull(savedTask);
        assertEquals(taskMock.getId(), savedTask.getId());
        assertEquals("title", savedTask.getTitle());
        assertEquals("content", savedTask.getContent());
    }

    @Test
    void shouldDeleteTask()  {
        //Given
        Task taskMock = new Task(1L, "title", "content");
        when(taskRepository.save(taskMock)).thenReturn(taskMock);
        when(taskRepository.findAll()).thenReturn(List.of());
        Task savedTask = dbService.saveTask(taskMock);
        //When
        dbService.deleteTask(savedTask.getId());
        List<Task> list = dbService.getAllTasks();
        //Then
        assertEquals(list.size(), 0);
    }
}