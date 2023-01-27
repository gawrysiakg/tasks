package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void shouldMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "task title", "task content");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(1L, task.getId());
        assertEquals("task title", task.getTitle());
        assertEquals("task content", task.getContent());
    }

    @Test
    void shouldMapToTaskDto() {
        //Given
        Task task = new Task(2L, "task title", "task content");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(2L, taskDto.getId());
        assertEquals("task title", taskDto.getTitle());
        assertEquals("task content", taskDto.getContent());
    }

    @Test
    void shouldMapToTaskDtoList() {
        //Given
        Task task1 = new Task(1L, "task title", "task content");
        Task task2 = new Task(2L, "task title", "task content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);
        //Then
        assertNotNull(taskDtos);
        assertEquals(2, taskDtos.size());
        assertEquals(2L, taskDtos.get(1).getId());
        taskDtos.forEach(taskDto -> {
            assertEquals("task title", taskDto.getTitle());
            assertEquals("task content", taskDto.getContent());
        });
    }

}