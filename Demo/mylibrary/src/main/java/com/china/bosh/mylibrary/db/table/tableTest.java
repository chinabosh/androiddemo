package com.china.bosh.mylibrary.db.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author lzq
 * @date 2018/8/1
 */

@Entity
public class tableTest {
    @Id
    public int id;
    @Property
    public String property;
    @Generated(hash = 1804822643)
    public tableTest(int id, String property) {
        this.id = id;
        this.property = property;
    }
    @Generated(hash = 988962298)
    public tableTest() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getProperty() {
        return this.property;
    }
    public void setProperty(String property) {
        this.property = property;
    }
}
