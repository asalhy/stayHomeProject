package com.stayhome.web.rest.vm;

import javax.validation.constraints.NotNull;

public class AssignRequestVM {

    @NotNull
    private Long assigneeId;

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }
}
