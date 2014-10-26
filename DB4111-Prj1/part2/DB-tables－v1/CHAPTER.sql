CREATE TABLE CHAPTER(
	course_id		INTEGER,
	chapter_title	VARCHAR2(100),
	PRIMARY KEY (course_id, chapter_title),
	FOREIGN KEY (course_id) REFERENCES COURSE (course_id)
			ON DELETE CASCADE
);