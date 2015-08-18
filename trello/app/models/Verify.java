package models;

import com.avaje.ebean.Model;

/**
 * Created by zemoso07 on 14/8/15.
 */
public class Verify extends Model{
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
