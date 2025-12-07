package org.atividade04.model;

public class Lesson {
    private int id;
    private int courseId;
    private String title;
    private String content;
    private int position;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lesson{");
        sb.append("id=").append(id);
        sb.append(", courseId=").append(courseId);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", position=").append(position);
        sb.append('}');
        return sb.toString();
    }
}
