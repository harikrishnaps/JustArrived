package app.JustArrived.harichand.com.JustArrived;

import app.JustArrived.harichand.com.JustArrived.models.TaskModel;
import app.JustArrived.harichand.com.JustArrived.utils.TaskStateUtil;

/**
 * A wrapper class for tasks and it's state as {STATE_ACTIVE_SNOOZED, STATE_ACTIVE_NOT_SNOOZED,
 * STATE_UPCOMING, STATE_EXPIRED, STATE_DONE}.
 *
 * @author sreehari
 */

public class TaskStateWrapper {

    private TaskModel task;
    @TaskStateUtil.TaskState
    private int state;
    private String locationName;

    public TaskStateWrapper() {
    }

    public TaskStateWrapper(TaskModel task, @TaskStateUtil.TaskState int state) {
        this.task = task;
        this.state = state;
    }

    public TaskStateWrapper(TaskModel task, @TaskStateUtil.TaskState int state, String
            locationName) {
        this.task = task;
        this.state = state;
        this.locationName = locationName;
    }

    public TaskModel getTask() {
        return task;
    }

    public void setTask(TaskModel task) {
        this.task = task;
    }

    public int getState() {
        return state;
    }

    public void setState(@TaskStateUtil.TaskState int state) {
        this.state = state;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
