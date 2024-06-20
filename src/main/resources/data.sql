CREATE SCHEMA IF NOT EXISTS exam_db;

INSERT INTO test_class (name, age)
VALUES ('John', 25),
       ('Jane', 30),
       ('Doe', 35);


INSERT INTO participant (full_name, gender, age, adjacent_club, age_group)
VALUES ('John Doe', 'MALE', 25, 'Club A', 'ADULT'),
       ('Jane Doe', 'FEMALE', 30, 'Club B', 'ADULT');


INSERT INTO discipline (name, description, results_type) VALUES ('Discipline A', 'Description A', 'TIME'),
                                                              ('Discipline B', 'Description B', 'DISTANCE');


INSERT INTO participant_disciplines (participant_id, disciplines_id) VALUES (1, 1),
                                                                            (1, 2),
                                                                            (2, 1);

INSERT INTO result (result_date, time_value, distance_value, point_value, participant_id, discipline_id) VALUES ('2022-01-01', '00:10:00', NULL, 10.0, 1, 1),
                                                                                                                ('2022-01-02', NULL, 100.0, 20.0, 2, 2);
