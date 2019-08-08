package com.crud.tasks.domain;


import lombok.Getter;
import lombok.AllArgsConstructor

@Getter
@AllArgsConstructor
public class TaskDto {
    private long id;
    private String title;
    private String content;

}
