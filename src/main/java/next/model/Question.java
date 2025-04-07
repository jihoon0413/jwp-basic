package next.model;

import java.util.Date;

public class Question {
    private Long questionId;
    private String writer;
    private String title;
    private String contents;
    private Date createDate;
    private int countOfAnswer;

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

    public long getCreateDate() {
        return createDate.getTime();
    }

    public int getCountOfAnswer() {
        return countOfAnswer;
    }
}
