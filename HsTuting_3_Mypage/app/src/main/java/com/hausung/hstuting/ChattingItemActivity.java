package com.hausung.hstuting;

// 리사이클러뷰에 보여줄 각각의 채팅방 리스트
public class ChattingItemActivity {
    String subject;
    String eachClass;
    String date;
    String time;
    String tutor;

    public ChattingItemActivity(String subject, String eachClass, String date, String time, String tutor) {
        this.subject = subject;
        this.eachClass = eachClass;
        this.date = date;
        this.time = time;
        this.tutor = tutor;
    }

    public String getsubject() {
        return subject;
    }

    public String getEachClass() {
        return eachClass;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getTutor() {
        return tutor;
    }

}
