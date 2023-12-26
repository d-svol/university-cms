
INSERT INTO university.groups (group_name) VALUES
  ('Group_A'),
  ('Group_B'),
  ('Group_C');

INSERT INTO university.students (first_name, last_name, group_id) VALUES
   ('StudentnameA', 'StudentLastnameA', 1),
   ('StudentnameB', 'StudentLastnameB', 2);

INSERT INTO university.professors (first_name, last_name) VALUES
  ('ProfessornameA', 'ProfessorLastnameA'),
  ('ProfessornameB', 'ProfessorLastnameB');

UPDATE university.professors SET faculty = 'Philosophy' WHERE id = 1;
UPDATE university.professors SET faculty = 'Biology' WHERE id = 2;

INSERT INTO university.courses (course_name) VALUES
  ('Philosophy'),
  ('Biology'),
  ('Sociology');

INSERT INTO university.groups_courses (group_id, course_id) VALUES
  (1, 1),
  (2, 2);

INSERT INTO university.professors_courses (professor_id, course_id) VALUES
  (1, 1),
  (2, 2);

INSERT INTO university.schedule (professor_id, course_id, group_id, schedule_start, schedule_end, schedule_description) VALUES
  (1, 1, 1, '2023-01-10 08:00:00', '2023-01-10 10:00:00', 'Philosophy description'),
  (2, 2, 2, '2023-01-12 09:00:00', '2023-01-12 11:00:00', 'Biology description');
