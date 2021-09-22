BEGIN TRANSACTION;
CREATE TABLE LECTURER
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    card_uid   TEXT,
    card_id    INTEGER,
    first_name TEXT,
    last_name  TEXT,
    patronymic TEXT,
    phone      TEXT,
    email      TEXT,
    image      BLOB
);
CREATE TABLE SCHEDULE_VERSION
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    start_date TEXT,
    end_date   TEXT
);
CREATE TABLE SCHEDULE
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    begin      TEXT,
    end        TEXT,
    number     INTEGER,
    version_id INTEGER,
    FOREIGN KEY (version_id) REFERENCES SCHEDULE_VERSION (id)
);
CREATE TABLE DEPARTMENT
(
    id           INTEGER PRIMARY KEY AUTO_INCREMENT,
    name         TEXT,
    abbreviation TEXT
);
CREATE TABLE DISCIPLINE
(
    id              INTEGER PRIMARY KEY AUTO_INCREMENT,
    name            TEXT,
    description     TEXT,
    create_date     TEXT,
    active          INTEGER,
    expiration_date TEXT
);
CREATE TABLE STREAM
(
    id              INTEGER PRIMARY KEY AUTO_INCREMENT,
    name            TEXT,
    image           BLOB,
    description     TEXT,
    create_date     TEXT,
    lecturer_id     INTEGER,
    discipline_id   INTEGER,
    department_id   INTEGER,
    course          INTEGER,
    active          INTEGER,
    expiration_date TEXT,
    lecture_count   INTEGER,
    practical_count INTEGER,
    lab_count       INTEGER,
    FOREIGN KEY (lecturer_id) REFERENCES LECTURER (id),
    FOREIGN KEY (discipline_id) REFERENCES DISCIPLINE (id),
    FOREIGN KEY (department_id) REFERENCES DEPARTMENT (id)
);
CREATE TABLE GROUP_TYPE
(
    id   INTEGER,
    name TEXT
);
CREATE TABLE STUDENT
(
    id          INTEGER PRIMARY KEY AUTO_INCREMENT,
    card_uid    TEXT,
    card_id     TEXT,
    first_name  TEXT,
    last_name   TEXT,
    patronymic  TEXT,
    phone       TEXT,
    email       TEXT,
    image       BLOB,
    record_book TEXT
);
CREATE TABLE STUDY_GROUP
(
    id              INTEGER PRIMARY KEY AUTO_INCREMENT,
    name            TEXT,
    department_id   INTEGER,
    type_id         INTEGER,
    image           BLOB,
    active          INTEGER,
    expiration_date TEXT,
    praepostor_id   INTEGER REFERENCES STUDENT (id),
    FOREIGN KEY (department_id) REFERENCES DEPARTMENT (id),
    FOREIGN KEY (type_id) REFERENCES GROUP_TYPE (id)
);
CREATE TABLE LESSON_TYPE
(
    id   INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    type TEXT
);
CREATE TABLE LESSON
(
    id           INTEGER PRIMARY KEY AUTO_INCREMENT,
    name         TEXT,
    description  TEXT,
    stream_id    INTEGER,
    create_date  TEXT,
    type_id      INTEGER,
    group_id     INTEGER,
    DATE         TEXT,
    SCHEDULE_ID  INTEGER REFERENCES SCHEDULE (id),
    index_number INTEGER,
    checked      integer not null default 0,
    FOREIGN KEY (stream_id) REFERENCES STREAM (id),
    FOREIGN KEY (type_id) REFERENCES LESSON_TYPE (id),
    FOREIGN KEY (group_id) REFERENCES STUDY_GROUP (id)
);
CREATE TABLE STUDENT_GROUP
(
    id         INTEGER PRIMARY KEY AUTO_INCREMENT,
    student_id INTEGER,
    group_id   INTEGER,
    praepostor INTEGER,
    FOREIGN KEY (student_id) REFERENCES STUDENT (id),
    FOREIGN KEY (group_id) REFERENCES STUDY_GROUP (id)
);
CREATE TABLE STREAM_GROUP
(
    id        INTEGER PRIMARY KEY AUTO_INCREMENT,
    stream_id INTEGER,
    group_id  INTEGER,
    FOREIGN KEY (stream_id) REFERENCES STREAM (id),
    FOREIGN KEY (group_id) REFERENCES STUDY_GROUP (id)
);
CREATE TABLE NOTE
(
    id          INTEGER PRIMARY KEY AUTO_INCREMENT,
    lecturer_id INTEGER,
    type        TEXT,
    entity_id   INTEGER,
    description TEXT,
    create_date TEXT,
    FOREIGN KEY (lecturer_id) REFERENCES LECTURER (id)
);
CREATE TABLE STUDENT_LESSON
(
    id                INTEGER PRIMARY KEY AUTO_INCREMENT,
    student_id        INTEGER,
    lesson_id         INTEGER,
    registered        INTEGER DEFAULT 0,
    registration_time TEXT,
    registration_type TEXT,
    mark              TEXT,
    mark_time         TEXT,
    FOREIGN KEY (student_id) REFERENCES STUDENT (id),
    FOREIGN KEY (lesson_id) REFERENCES LESSON (id)
);
CREATE TABLE ALARM
(
    id     INTEGER PRIMARY KEY AUTO_INCREMENT,
    active INTEGER DEFAULT 0,
    time   INTEGER,
    volume DECIMAL(1, 1),
    sound  TEXT
);
CREATE TABLE NOTIFICATION_SETTING
(
    id     INTEGER PRIMARY KEY AUTO_INCREMENT,
    type   TEXT,
    active INTEGER       DEFAULT 0,
    data   TEXT,
    volume DECIMAL(1, 1) DEFAULT 1,
    sound  TEXT
);
CREATE TABLE STUDENT_NOTIFICATION
(
    id          INTEGER PRIMARY KEY AUTO_INCREMENT,
    student_id  INTEGER,
    active      INTEGER DEFAULT 0,
    create_date TEXT,
    description TEXT,
    FOREIGN KEY (student_id) REFERENCES STUDENT (id)
);
COMMIT;