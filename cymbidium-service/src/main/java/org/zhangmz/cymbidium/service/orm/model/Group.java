package org.zhangmz.cymbidium.service.orm.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "groups")
public class Group {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column groups.id
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column groups.code
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column groups.group_name
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    private String groupName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column groups.admin_name
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    private String adminName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column groups.phone
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column groups.register_date
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    private Date registerDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column groups.status
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column groups.note
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    private String note;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column groups.id
     *
     * @return the value of groups.id
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column groups.id
     *
     * @param id the value for groups.id
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column groups.code
     *
     * @return the value of groups.code
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column groups.code
     *
     * @param code the value for groups.code
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column groups.group_name
     *
     * @return the value of groups.group_name
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column groups.group_name
     *
     * @param groupName the value for groups.group_name
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column groups.admin_name
     *
     * @return the value of groups.admin_name
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column groups.admin_name
     *
     * @param adminName the value for groups.admin_name
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column groups.phone
     *
     * @return the value of groups.phone
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column groups.phone
     *
     * @param phone the value for groups.phone
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column groups.register_date
     *
     * @return the value of groups.register_date
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")  
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column groups.register_date
     *
     * @param registerDate the value for groups.register_date
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column groups.status
     *
     * @return the value of groups.status
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column groups.status
     *
     * @param status the value for groups.status
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column groups.note
     *
     * @return the value of groups.note
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column groups.note
     *
     * @param note the value for groups.note
     *
     * @mbggenerated Thu Mar 31 14:43:39 CST 2016
     */
    public void setNote(String note) {
        this.note = note;
    }
    
    /*************************************************************************
 	 * 说明：增加分页查询
 	 * 作者：张孟志
 	 * 日期：2016-03-31
 	 ************************************************************************/
    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;

	public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}