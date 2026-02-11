package org.atividade08.bean;

import java.sql.SQLException;

import org.atividade08.dao.LessonDao;
import org.atividade08.model.Lesson;

public class LessonBean {
    private Lesson lesson;
    private int id;
    private String title;
    private String content;
    private String notesFile;
    private String errorMessage;
    private LessonDao lessonDao;

    public boolean initialize(int id) {
        lessonDao = new LessonDao();
        try {
            this.lesson = lessonDao.findById(id);
        } catch (SQLException e) {
            this.errorMessage = "Erro ao buscar aula.";
            return false;
        }
        if (lesson == null) {
            this.errorMessage = "Aula não encontrada.";
            return false;
        }
        this.id = id;
        this.title = lesson.getTitle();
        this.content = lesson.getContent();
        this.notesFile = lesson.getNotesFile();
        return true;
    }

    public void updateNotes(String filename) throws Exception {
        this.notesFile = filename;
        this.lesson.setNotesFile(notesFile);
        lessonDao.update(lesson);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getNotesFile() {
        return notesFile;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
