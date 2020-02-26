package cn.rubintry.self.model;

/**
 * @author logcat
 * 用户登录信息
 */
public class LoginModel {

    /**
     * id : 19
     * username : test
     * createTime : 20200226155304
     */

    private int id;
    private String username;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username == null ? "" : username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
