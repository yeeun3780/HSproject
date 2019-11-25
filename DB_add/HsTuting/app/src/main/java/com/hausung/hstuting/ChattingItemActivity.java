package com.hausung.hstuting;

// 리사이클러뷰에 보여줄 각각의 채팅방 리스트
public class ChattingItemActivity {
    String subject;
    String tutor;

    public ChattingItemActivity(String subject, String tutor) {
        this.subject = subject;
        this.tutor = tutor;
    }

    public String getsubject() {
        return subject;
    }

    public void setsubject(String subject) {
        this.subject = subject;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }
}
