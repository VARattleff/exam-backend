
CREATE SCHEMA IF NOT EXISTS exam_db;


INSERT INTO participant (full_name, gender, age, adjacent_club, age_group, country)
VALUES
    ('John Doe', 'MALE', 25, 'Club A', 'ADULT', 'DENMARK'),
    ('Jane Doe', 'FEMALE', 30, 'Club B', 'ADULT', 'DENMARK'),
    ('Alice Smith', 'FEMALE', 28, 'Club A', 'ADULT', 'DENMARK'),
    ('Bob Johnson', 'MALE', 32, 'Club B', 'ADULT', 'DENMARK');


INSERT INTO discipline (name, description, results_type)
VALUES
    ('Discipline A', 'Description A', 'TIME'),
    ('Discipline B', 'Description B', 'DISTANCE'),
    ('Discipline C', 'Description C', 'POINTS');


INSERT INTO participant_disciplines (participant_id, disciplines_id)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 1),
    (2, 2),
    (3, 1),
    (3, 3),
    (4, 2),
    (4, 3);


INSERT INTO result (result_date, time_value, distance_value, point_value, participant_id, discipline_id)
VALUES
    ('2022-01-01', '00:10:00', NULL, 10.0, 1, 1),
    ('2022-01-02', NULL, 100.0, 20.0, 2, 2),
    ('2022-01-03', NULL, NULL, 30.0, 1, 3),
    ('2022-01-04', '00:12:00', NULL, 15.0, 2, 1),
    ('2022-01-05', NULL, 120.0, 25.0, 1, 2),
    ('2022-01-06', NULL, NULL, 35.0, 2, 3),
    ('2022-01-07', '00:09:00', NULL, 20.0, 3, 1),
    ('2022-01-08', NULL, 110.0, 22.0, 4, 2),
    ('2022-01-09', NULL, NULL, 32.0, 3, 3),
    ('2022-01-10', '00:11:00', NULL, 18.0, 4, 1);


