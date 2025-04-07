package next.model;

import java.util.Date;

public class Answer {
    private Long answerId;
    private String writer;
    private String contents;
    private Date createDate;
    private Long questionId;


    public Answer(String writer, String Contents, Long questionId) {
        this(0L, writer, Contents, new Date(), questionId);
    }

    public Answer(Long answerId, String writer, String contents, Date createDate, Long questionId) {
        this.answerId = answerId;
        this.writer = writer;
        this.contents = contents;
        this.createDate = createDate;
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public long getTimeFromCreateDate() {
        return this.createDate.getTime();
    }

    @Override
    public String toString() {
        return "Answer [answerId=" + answerId + ", writer=" + writer + ", contents=" + contents + ", createdDate="
                + createDate + ", questionId=" + questionId + "]";
    }
}
