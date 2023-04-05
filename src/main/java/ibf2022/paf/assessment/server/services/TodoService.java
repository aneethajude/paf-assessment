package ibf2022.paf.assessment.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;

// TODO: Task 7

@Service
public class TodoService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    public String upsertTask(List<Task> taskList, String userName) {
        String cUserId = null;
        User user = new User();        
        Optional<User> userList = userRepository.findUserByUsername(userName);
        if(!userList.isPresent()) {
            user.setUsername(userName);
            user.setName(userName.substring(0, 1).toUpperCase() + userName.substring(1));
            cUserId = userRepository.insertUser(user);
        } else {
            user = userList.get();
            cUserId = user.getUserId();
        }

        if(cUserId != null)
            taskRepository.upsertTask(taskList, cUserId);

        return "";
    }
        
}
