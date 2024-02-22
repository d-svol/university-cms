INSERT INTO university (name) VALUES ('National University of Ukraine “Kyiv”');

INSERT INTO faculty(name, university_id) VALUES
('Faculty of Computer Science', 1),
('Faculty of History', 1);

INSERT INTO courses (name, description, faculty_id) VALUES
('Mathematics', 'Advanced Mathematics', 1),
('Computer Science', 'Introduction to Programming', 1),
('History', 'World History', 2);

INSERT INTO groups(course_id, name) VALUES
(1, 'AA-01'), -- course_id == 'Mathematics'
(2, 'BB-02'); -- course_id == 'Computer Science'

INSERT INTO role(name) VALUES
('ADMIN'),
('STUDENT'),
('TEACHER'),
('STUFF');

INSERT INTO user_entity (username, password, role_id) VALUES
('admin', '$2a$10$kGYYgeP1xwODAboj2ehPmOiaZoRB/7nfV42exEci0Bu/O5YgA7wKm', 1), -- password admin
('student', '$2a$10$YJSevv8DY85Xkvyd4RZkKuXdFMjJN6jRbB7xWkj52qvSHo0boU7RW', 2), -- password student
('professor', '$2a$10$IAWlrclL7L.CrdXuu9q7PuclSV7zLVBAMk8Qnt0v9.spUylS6XnaG', 3), -- password professor
('AdminUsername', '$2a$10$kGYYgeP1xwODAboj2ehPmOiaZoRB/7nfV42exEci0Bu/O5YgA7wKm', 1), -- password admin
('StudentUsername', '$2a$10$YJSevv8DY85Xkvyd4RZkKuXdFMjJN6jRbB7xWkj52qvSHo0boU7RW', 2),-- password student
('ProfessorUsername', '$2a$10$IAWlrclL7L.CrdXuu9q7PuclSV7zLVBAMk8Qnt0v9.spUylS6XnaG', 3), -- password professor
('stuff', '$2a$10$XFaYvwQh7UApKlRsFZM.IuQcXma6zD5P1lLt0914aDDym1US2GPC2', 4); -- password stuff

INSERT INTO student(id, group_id) VALUES
(2, 1), -- 'student' 'AA-01'
(5, 1); -- 'StudentUsername' 'AA-01'

INSERT INTO professor (user_id) VALUES
(3), -- 'professor'
(6); -- 'TeacherUsername'

INSERT INTO professor_course (professor_id, course_id) VALUES
(1, 1), -- 'professor' assigned to 'Mathematics'
(1, 2), -- 'professor' assigned to 'Computer Science'
(2, 3); -- 'TeacherUsername' assigned to 'History'

INSERT INTO schedule (course_id, group_id, professor_id, start_time, end_time, date) VALUES
(1, 1, 1, '09:00:00', '11:00:00', '2023-07-07'), -- course-'Mathematics' / group-'AA-01'/ professor-'professor'
(2, 1, 1, '10:00:00', '12:00:00', '2023-07-08'), -- course-'Computer Science' / group-'AA-01' / professor-'professor'
(2, 2, 2, '18:00:00', '22:00:00', '2024-02-21'), -- course-'Computer Science' / group-'BB-02' / professor-'ProfessorUsername'
(3, 1, 1, '14:00:00', '16:00:00', '2023-07-07'); -- course-'History' / group-'AA-01' / professor-'professor'
