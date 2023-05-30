package com.example.task_management.models.composite;
import com.example.task_management.domain.enums.TaskType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class SubTask implements TaskComponent {

    private Integer id;

    private String title;

    private String description;

    private TaskType type;

    private LocalDateTime deadline;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
