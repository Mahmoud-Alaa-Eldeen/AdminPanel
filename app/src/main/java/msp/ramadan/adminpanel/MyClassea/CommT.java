package msp.ramadan.adminpanel.MyClassea;

/**
 * Created by ramadan on 6/5/2017.
 */
public class CommT {

     String CommTIDD;
    String CommentContent;
    String CommentCrTime;
    String CommentUser;

    public CommT() {
    }

    public CommT(String eventName, String commentContent, String commentCrTime, String commentUser) {
        CommTIDD = eventName;
        CommentContent = commentContent;
        CommentCrTime = commentCrTime;
        CommentUser = commentUser;
    }

    public String getCommTIDD() {
        return CommTIDD;
    }

    public void setCommTIDD(String commTIDD) {
        CommTIDD = commTIDD;
    }

    public String getCommentContent() {
        return CommentContent;
    }

    public void setCommentContent(String commentContent) {
        CommentContent = commentContent;
    }

    public String getCommentCrTime() {
        return CommentCrTime;
    }

    public void setCommentCrTime(String commentCrTime) {
        CommentCrTime = commentCrTime;
    }

    public String getCommentUser() {
        return CommentUser;
    }

    public void setCommentUser(String commentUser) {
        CommentUser = commentUser;
    }
}
