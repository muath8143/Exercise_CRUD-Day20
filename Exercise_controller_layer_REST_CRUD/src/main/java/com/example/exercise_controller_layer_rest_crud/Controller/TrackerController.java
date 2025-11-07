package com.example.exercise_controller_layer_rest_crud.Controller;

import com.example.exercise_controller_layer_rest_crud.Api.ApiResponse;
import com.example.exercise_controller_layer_rest_crud.Model.TrackerSystem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/tracker")
public class TrackerController {
    ArrayList<TrackerSystem> tracker=new ArrayList<>();


    @PostMapping("/create/{id}")
    public ApiResponse CreateTask (@PathVariable int id, @RequestBody TrackerSystem trackerSystem){
        trackerSystem.setId(id);
        tracker.add(trackerSystem);
        return new ApiResponse("Created task was successfully");
    }

    @GetMapping("/get")
    public ArrayList<TrackerSystem> GetTasks(){
        return tracker;
    }

    @PutMapping("/update/{index}")
    public ApiResponse UpdateTask(@PathVariable int index ,@RequestBody TrackerSystem trackerSystem){
        tracker.set(index,trackerSystem);
        return new ApiResponse("the task with index: "+index+" was updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse DeleteTask(@PathVariable int index){
        tracker.remove(index);
        return new ApiResponse("the task have index: "+index+" was deleted successfully");
    }

    @PutMapping("/updatestatus/{index}")
    public ApiResponse UpdateTaskStatus(@PathVariable int index,@RequestBody boolean status ){
        int i= -1;
        for(TrackerSystem t:tracker){
            i++;
            if (i==index){
                t.setStatus(status);
                break;
            }

        }
        if (status){
            return new ApiResponse ("task is done");
        }
        return new ApiResponse("task is not done");
    }

    @GetMapping("/search/{title}")
    public TrackerSystem getTaskByTitle(@PathVariable String title){
        for(TrackerSystem t:tracker){
            if (t.getTitle().equalsIgnoreCase(title)){
                return t;
            }
        }
        return null;

    }
}
