package ru.popovich.emergencyassist.dto;

/**
 * Class DTO to impact TASK for Hardup user
 *
 *
 */

public class TaskSocialServiceIds {

    private Long userHardupId;

    private Long userEmployeeId;

    private Long sid;

    public Long getUserHardupId() {
        return userHardupId;
    }

    public void setUserHardupId(Long userHardupId) {
        this.userHardupId = userHardupId;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getUserEmployeeId() {
        return userEmployeeId;
    }

    public void setUserEmployeeId(Long userEmployeeId) {
        this.userEmployeeId = userEmployeeId;
    }
}
