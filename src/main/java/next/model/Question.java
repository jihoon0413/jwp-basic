package next.model;

import java.sql.Timestamp;
import java.util.Date;

public class Question {
    private Long questionId;
    private String writer;
    private String title;
    private String contents;
    private Date createDate;
    private int countOfAnswer;

    public Question(String writer, String title, String contents) {
        this(0L ,writer, title, contents, new Date(), 0);
    }

    public Question(Long questionId, String writer, String title, String contents, Date createDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createDate = createDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public long getTimeFromCreateDate() {
        return this.createDate.getTime();
    }

    public int getCountOfAnswer() {
        return countOfAnswer;
    }

    public void update(Question newQuestion) {
        this.title = newQuestion.title;
        this.contents = newQuestion.contents;
    }

    public boolean isSameUser(User user) {
        return user.isSameUser(this.writer);
    }
}
