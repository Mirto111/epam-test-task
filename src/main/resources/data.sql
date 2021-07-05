insert into projects(name)
values ('project1'),
       ('project2'),
       ('project3'),
       ('project4');
INSERT INTO users(name)
VALUES ('user1'),
       ('user2'),
       ('user3'),
       ('user4'),
       ('user5');

INSERT INTO project_user(project_id, user_Id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (3, 4),
       (3, 5);

INSERT INTO tasks(name, description, closed ,user_id, project_Id)
VALUES ('task1', 'desc1', false ,1, 1),
       ('task2', 'desc2', false ,2, 1),
       ('task3', 'desc3', false ,3, 2),
       ('task4', 'desc4', true, 3, 2),
       ('task5', 'desc5', false, 4, 3),
       ('task6', 'desc6', false, 4, 3),
       ('task7', 'desc7',true, 5, 3),
       ('task8', 'desc8',false, 1, 2),
       ('task9', 'desc9',true, 1, 3),
       ('task10', 'desc10', false, 3, 2);

INSERT INTO sub_tasks(name, description, closed, task_id)
VALUES ('sub1', 'descSub1', false, 1),
       ('sub2', 'descSub2', false, 1),
       ('sub3', 'descSub3', false, 1),
       ('sub4', 'descSub4', false, 1),
       ('sub5', 'descSub5', false, 1),
       ('sub6', 'descSub6', false, 1),
       ('sub7', 'descSub7', false, 1),
       ('sub8', 'descSub8', false, 1),
       ('sub9', 'descSub9', false, 1),
       ('sub10', 'descSub10', false, 1);

INSERT INTO subtask_subt(subtask_id, sustask_sub_id)
VALUES (1, 2),
       (2, 3),
       (3, 4),
       (4, 5),
       (6,7),
       (6,8),
       (9,10);

--          task1
--          /|\
--         / | \
--        /  |  \
--       /   |   \
--      /    |    \
--    sub1  sub6   sub9
--     |     |     |
--   sub2   / \    sub10
--     |   /   \
--   sub3 sub7 sub8
--    |
--   sub4
--     |
--   sub5