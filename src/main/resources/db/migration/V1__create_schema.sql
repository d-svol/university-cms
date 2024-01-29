CREATE TABLE university (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE faculty (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    university_id INTEGER,
    FOREIGN KEY (university_id) REFERENCES university(id)
);

CREATE TABLE courses (
    id  SERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255) NOT NULL,
    faculty_id INTEGER      NOT NULL,
    FOREIGN KEY (faculty_id) REFERENCES faculty(id) ON DELETE CASCADE
);

CREATE TABLE groups (
    id SERIAL PRIMARY KEY,
    course_id INTEGER      NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

CREATE TABLE role (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE user_entity (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id    INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE student (
	user_id SERIAL PRIMARY KEY,
	group_id INTEGER,
	FOREIGN KEY (user_id) REFERENCES user_entity(id),
	FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE
);

CREATE TABLE professor (
    id SERIAL PRIMARY KEY,
    user_id      INTEGER,
    FOREIGN KEY (user_id) REFERENCES user_entity(id)
);

CREATE TABLE schedule (
    id  SERIAL PRIMARY KEY,
    course_id    INTEGER   NOT NULL,
    group_id INTEGER   NOT NULL,
    professor_id INTEGER NOT NULL,
    start_time   TIMESTAMP NOT NULL,
    end_time     TIMESTAMP NOT NULL,
    date         DATE     NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE,
    FOREIGN KEY (professor_id) REFERENCES professor(id) ON DELETE CASCADE
);