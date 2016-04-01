package org.zhangmz.cymbidium.batch.orm.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "accounts")
public class Account {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.id
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.phone
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.name
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.email
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.hash_password
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    private String hashPassword;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.salt
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    private String salt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.register_date
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    private Date registerDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.group_id
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    private Integer groupId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column accounts.status
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.id
     *
     * @return the value of accounts.id
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.id
     *
     * @param id the value for accounts.id
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.phone
     *
     * @return the value of accounts.phone
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.phone
     *
     * @param phone the value for accounts.phone
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.name
     *
     * @return the value of accounts.name
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.name
     *
     * @param name the value for accounts.name
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.email
     *
     * @return the value of accounts.email
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.email
     *
     * @param email the value for accounts.email
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.hash_password
     *
     * @return the value of accounts.hash_password
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public String getHashPassword() {
        return hashPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.hash_password
     *
     * @param hashPassword the value for accounts.hash_password
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.salt
     *
     * @return the value of accounts.salt
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public String getSalt() {
        return salt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.salt
     *
     * @param salt the value for accounts.salt
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.register_date
     *
     * @return the value of accounts.register_date
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.register_date
     *
     * @param registerDate the value for accounts.register_date
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.group_id
     *
     * @return the value of accounts.group_id
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.group_id
     *
     * @param groupId the value for accounts.group_id
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column accounts.status
     *
     * @return the value of accounts.status
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column accounts.status
     *
     * @param status the value for accounts.status
     *
     * @mbggenerated Fri Apr 01 11:12:32 CST 2016
     */
    public void setStatus(String status) {
        this.status = status;
    }
}