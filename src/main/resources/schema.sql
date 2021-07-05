DROP TABLE IF EXISTS subtask_subt;
DROP TABLE IF EXISTS sub_tasks;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS project_user;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS projects;

CREATE TABLE projects (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(100)
);
CREATE TABLE users (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE project_user (
    project_id INTEGER,
    user_id INTEGER,
    FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT project_user_idx PRIMARY KEY (project_id, user_id)
);

CREATE TABLE tasks (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(100),
    description VARCHAR(255),
    closed BOOLEAN,
    user_id INTEGER,
    project_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE
);

CREATE TABLE sub_tasks
(
    id  INT AUTO_INCREMENT  PRIMARY KEY,
    name        VARCHAR(100),
    description VARCHAR(255),
    closed      BOOLEAN,
    task_id     INTEGER,
    FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE
);

CREATE TABLE subtask_subt (
    subtask_id INTEGER,
    sustask_sub_id INTEGER,
    FOREIGN KEY (subtask_id) REFERENCES sub_tasks (id) ON DELETE CASCADE,
    FOREIGN KEY (sustask_sub_id) REFERENCES sub_tasks (id) ON DELETE CASCADE
)