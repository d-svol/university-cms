INSERT INTO university (name) VALUES ('National University of Ukraine “Kyiv”');

INSERT INTO faculty(university_id, name) VALUES
(1, 'Faculty of Computer Science'),
(1, 'Faculty of History');

INSERT INTO course (name, description, faculty_id) VALUES
('Mathematics', 'Advanced Mathematics', 1),
('Computer Science', 'Introduction to Programming', 1),
('History', 'World History', 2);


INSERT INTO groups(faculty_id, name) VALUES
(1, 'AA-01'),
(2, 'BB-02');

INSERT INTO role(name) VALUES
('ADMIN'),
('STUDENT'),
('TEACHER'),
('STUFF');

INSERT INTO user_entity (username, password, role_id, faculty_id) VALUES
('admin', '$2a$10$kGYYgeP1xwODAboj2ehPmOiaZoRB/7nfV42exEci0Bu/O5YgA7wKm', 1, 1), -- password admin
('student', '$2a$10$YJSevv8DY85Xkvyd4RZkKuXdFMjJN6jRbB7xWkj52qvSHo0boU7RW', 2, 2), -- password student
('professor', '$2a$10$IAWlrclL7L.CrdXuu9q7PuclSV7zLVBAMk8Qnt0v9.spUylS6XnaG', 3, 2), -- password professor
('AdminUsername', '$2a$10$kGYYgeP1xwODAboj2ehPmOiaZoRB/7nfV42exEci0Bu/O5YgA7wKm', 1, 1),
('StudentUsername', '$2a$10$kGYYgeP1xwODAboj2ehPmOiaZoRB/7nfV42exEci0Bu/O5YgA7wKm', 2, 2),
('TeacherUsername', '$2a$10$kGYYgeP1xwODAboj2ehPmOiaZoRB/7nfV42exEci0Bu/O5YgA7wKm', 3, 2);


INSERT INTO student(user_id, group_id) VALUES
(2, 1), -- 'student' 'AA-01'
(5, 1); -- 'StudentUsername' 'AA-01'

INSERT INTO professor (user_id)
SELECT id
FROM user_entity
WHERE role_id = 3;-- 'TeacherUsername' and 'professor'


INSERT INTO schedule (course_id, group_id, professor_id, start_time, end_time, date) VALUES
(1, 1, 1, '2023-07-07 09:00:00', '2023-07-07 11:00:00', '2023-07-07'), -- course-'Mathematics' / group-'AA-01'/ professor-'professor'
(2, 1, 1, '2023-07-08 10:00:00', '2023-07-08 12:00:00', '2023-07-08'), -- course-'Computer Science' / group-'AA-01' / professor-'professor'
(3, 1, 1, '2023-07-07 14:00:00', '2023-07-07 16:00:00', '2023-07-07'); -- course-'History' / group-'AA-01' / professor-'professor'
