package com.app.bean;

import java.util.Date;

public class Orders {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.movieid
     *
     * @mbggenerated
     */
    private Integer movieid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.moviename
     *
     * @mbggenerated
     */
    private String moviename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.seat
     *
     * @mbggenerated
     */
    private String seat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.status
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orders.createdate
     *
     * @mbggenerated
     */
    private Date createdate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.id
     *
     * @return the value of orders.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.id
     *
     * @param id the value for orders.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.movieid
     *
     * @return the value of orders.movieid
     *
     * @mbggenerated
     */
    public Integer getMovieid() {
        return movieid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.movieid
     *
     * @param movieid the value for orders.movieid
     *
     * @mbggenerated
     */
    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.moviename
     *
     * @return the value of orders.moviename
     *
     * @mbggenerated
     */
    public String getMoviename() {
        return moviename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.moviename
     *
     * @param moviename the value for orders.moviename
     *
     * @mbggenerated
     */
    public void setMoviename(String moviename) {
        this.moviename = moviename == null ? null : moviename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.seat
     *
     * @return the value of orders.seat
     *
     * @mbggenerated
     */
    public String getSeat() {
        return seat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.seat
     *
     * @param seat the value for orders.seat
     *
     * @mbggenerated
     */
    public void setSeat(String seat) {
        this.seat = seat == null ? null : seat.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.status
     *
     * @return the value of orders.status
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.status
     *
     * @param status the value for orders.status
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orders.createdate
     *
     * @return the value of orders.createdate
     *
     * @mbggenerated
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orders.createdate
     *
     * @param createdate the value for orders.createdate
     *
     * @mbggenerated
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}