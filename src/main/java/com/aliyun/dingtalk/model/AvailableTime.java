/**
 * Alibaba-inc.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.aliyun.dingtalk.model;

/**
 * 可用时间
 * @author shiyan
 * @version $Id: AvailableTime.java, v 0.1 2021-10-28 上午10:31 shiyan Exp $$
 */
public class AvailableTime {
    /**
     * 开始时间
     */
    private String gmtStart;

    /**
     * 结束时间
     */
    private String gmtEnd;

    /**
     * Getter method for property gmtStart.
     *
     * @return property value of gmtStart
     */
    public String getGmtStart() {
        return gmtStart;
    }

    /**
     * Setter method for property gmtStart.
     *
     * @param gmtStart value to be assigned to property gmtStart
     */
    public void setGmtStart(String gmtStart) {
        this.gmtStart = gmtStart;
    }

    /**
     * Getter method for property gmtEnd.
     *
     * @return property value of gmtEnd
     */
    public String getGmtEnd() {
        return gmtEnd;
    }

    /**
     * Setter method for property gmtEnd.
     *
     * @param gmtEnd value to be assigned to property gmtEnd
     */
    public void setGmtEnd(String gmtEnd) {
        this.gmtEnd = gmtEnd;
    }
}