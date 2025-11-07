package com.example.exercise_controller_layer_rest_crud.Model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrackerSystem {
    private int id;
    private String title;
    private String description;
    private boolean status;
}
