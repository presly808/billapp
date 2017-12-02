package ua.artcode.billapp.dto;

/**
 * Created by serhii on 02.12.17.
 */
public class ResponseMessage {

    private String title;
    private String body;

    public ResponseMessage() {
    }

    public ResponseMessage(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
