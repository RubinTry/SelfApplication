package cn.rubintry.self.model;

/**
 * @author logcat
 */
public class ArticleModel {


    /**
     * id : 67
     * fieldId : 3fe5e15908554f728f98ac520a9902a4
     * content_url : https://juejin.im/post/5b09fe716fb9a07aa114a6d9
     * title : Flutter
     */

    private int id;
    private String fieldId;
    private String content_url;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
