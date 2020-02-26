package cn.rubintry.self.common.utils;

public class PasswordUtils {
    /**
     * 密码复杂度索引
     * @param  0: 是否含有大写字母
     * @param  1: 是否含有小写字母
     * @param  2: 是否含有数字
     * @param  2: 是否含有符号
     */
    private static int[] index = {0 , 0 , 0 , 0};
    public static boolean isStrongPassword(String password){
        boolean isStrong = false;
        for (int i = 0; i < password.length(); i++) {
            if(password.charAt(i) >= 65 && password.charAt(i) <= 90){
                //如果有大写字母
                index[0] = 1;
            }else if(password.charAt(i) >= 97 && password.charAt(i) <= 122){
                //如果有小写字母
                index[1] = 1;
            }else if(password.charAt(i) >= 48 && password.charAt(i) <= 57){
                //如果含有数字
                index[2] = 1;
            }else{
                index[3] = 1;
            }

            if(index[0] == 1 && index[1] == 1 && index[2] == 1 && index[3] == 1){
                isStrong = true;
                break;
            }
        }
        return isStrong;
    }
}
