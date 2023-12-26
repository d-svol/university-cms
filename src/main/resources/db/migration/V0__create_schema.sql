CREATE SCHEMA university;

CREATE TABLE university.groups (
  id SERIAL PRIMARY KEY,
  group_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE university.students (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  group_id INTEGER,
  FOREIGN KEY (group_id) REFERENCES university.groups (id)
);

CREATE TABLE university.professors (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  faculty VARCHAR(255)
);

CREATE TABLE university.courses (
  id SERIAL PRIMARY KEY,
  course_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE university.schedule (
  id SERIAL PRIMARY KEY,
  professor_id INTEGER NOT NULL,
  course_id INTEGER NOT NULL,
  group_id INTEGER NOT NULL,
  schedule_start TIMESTAMP NOT NULL,
  schedule_end TIMESTAMP NOT NULL,
  schedule_description TEXT,
  FOREIGN KEY (professor_id) REFERENCES university.professors (id),
  FOREIGN KEY (course_id) REFERENCES university.courses (id),
  FOREIGN KEY (group_id) REFERENCES university.groups (id)
);

CREATE TABLE university.professors_courses (
  id SERIAL PRIMARY KEY,
  professor_id INTEGER NOT NULL,
  course_id INTEGER NOT NULL,
  FOREIGN KEY (professor_id) REFERENCES university.professors (id),
  FOREIGN KEY (course_id) REFERENCES university.courses (id),
  UNIQUE (professor_id, course_id)
);

CREATE TABLE university.groups_courses (
  id SERIAL PRIMARY KEY,
  group_id INTEGER NOT NULL,
  course_id INTEGER NOT NULL,
  FOREIGN KEY (group_id) REFERENCES university.groups (id),
  FOREIGN KEY (course_id) REFERENCES university.courses (id),
  UNIQUE (group_id, course_id)
);