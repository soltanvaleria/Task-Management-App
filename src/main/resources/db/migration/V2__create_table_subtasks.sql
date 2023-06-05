CREATE TABLE subtasks
(
    subtask_id          SERIAL PRIMARY KEY,
    subtask_title       VARCHAR(255) NOT NULL,
    subtask_description VARCHAR(255),
    subtask_type        VARCHAR(50)  NOT NULL,
    subtask_deadline    TIMESTAMP,
    task_id             INT          NOT NULL,
    is_done             BOOLEAN      NOT NULL,
    CONSTRAINT fk_task FOREIGN KEY (task_id) REFERENCES tasks (task_id)
);