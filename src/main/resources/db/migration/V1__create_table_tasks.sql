CREATE TABLE tasks
(
    task_id          SERIAL PRIMARY KEY,
    task_title       VARCHAR(255) NOT NULL,
    task_description VARCHAR(255),
    task_priority    VARCHAR(255)
);