package ibf2022.paf.assessment.server.controllers;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.repositories.UserRepository;
import ibf2022.paf.assessment.server.services.TodoService;


// TODO: Task 4, Task 8

@Controller
@RequestMapping(path="/task")
public class TasksController {
    @Autowired
    TodoService todoService;        

    @PostMapping
    public ModelAndView upsertTask(@RequestParam MultiValueMap<String, String> form) {
        ModelAndView modelAndView = null; 
        List<Task> taskList = new ArrayList<>();   
        Task task = new Task(); 
        String userName = form.getFirst("username");
        if(form.size() > 4) {
            for(int i = 0; i < (form.size() - 1) / 3; i++) {
                task = new Task(); 
                task.setDescription(form.getFirst("description-"+i));
                task.setDueDate(form.getFirst("dueDate-"+i));
                if(form.getFirst("priority-"+i) != null)
                    task.setPriority(Integer.parseInt(form.getFirst("priority-"+i)));
                taskList.add(task);
            }
        } else {
            task = new Task(); 
            task.setDescription(form.getFirst("description-0"));
            task.setDueDate(form.getFirst("dueDate-0"));
            if(form.getFirst("priority-0") != null)
                task.setPriority(Integer.parseInt(form.getFirst("priority-0")));
            taskList.add(task);
        }
        
        if(taskList != null && taskList.size() > 0) {
            todoService.upsertTask(taskList, userName);
            modelAndView = new ModelAndView("/result");
            modelAndView.addObject("taskCount", taskList.size());
            modelAndView.setStatus(HttpStatus.OK);
        } else {
            modelAndView = new ModelAndView("/error");
            modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return modelAndView;
    }
}
