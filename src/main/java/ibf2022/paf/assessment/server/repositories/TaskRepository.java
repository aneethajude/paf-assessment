package ibf2022.paf.assessment.server.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.Task;

// TODO: Task 6
@Repository
public class TaskRepository {

    public static final String SQL_INSERT_TASK ="insert into task(user_id, description, priority, due_date) values(?, ?, ?, ?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String insertTask(List<Task> taskList, String cUserId) {
        String responseString = null;
        try {
            if(taskList != null) {
                for(Task task : taskList) {
                    jdbcTemplate.update(SQL_INSERT_TASK, cUserId, task.getDescription(), task.getPriority(), task.getDueDate());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseString;
    }
}
