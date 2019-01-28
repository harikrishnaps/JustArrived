package app.JustArrived.harichand.com.JustArrived.utils;

import android.content.Context;
import androidx.annotation.NonNull;

import org.joda.time.LocalDate;

import java.util.Date;

import app.JustArrived.harichand.com.JustArrived.TaskRepository;
import app.JustArrived.harichand.com.JustArrived.database.DbConstants;
import app.JustArrived.harichand.com.JustArrived.models.TaskModel;
import app.JustArrived.harichand.com.JustArrived.notification.NotificationHelper;

/**
 * Contains actions performed commonly on tasks. Both AlarmActivity and Notifications will be
 * using this.
 *
 * @author sreehari
 */
public final class TaskActionUtils {

    public static void onTaskMarkedDone(@NonNull Context appContext, TaskModel task) {
        task.setLastTriggered(LocalDate.fromDateFields(new Date()));
        if (task.getRepeatType() == DbConstants.REPEAT_DAILY) {
            LocalDate today = LocalDate.fromDateFields(new Date());
            // If it's a repeatable reminder we've to check that it's last date is not today. If it
            // is, then it won't be upcoming anymore and hence should be marked as done.
            if (task.getEndDate() != null && task.getEndDate().compareTo(today) <= 0) {
                task.setIsDone(1);
            } else {
                // If the reminder is done only for today, we'll increment the nextStartDate only.
                task.setNextStartDate(today.plusDays(1));
            }
        } else {
            // Mark it as done for non-repeatable reminders.
            task.setIsDone(1);
        }
        TaskRepository repository = new TaskRepository(appContext);
        repository.updateTask(task);
    }

    public static void onTaskSnoozed(@NonNull Context appContext, TaskModel task) {
        task.setSnoozedAt(System.currentTimeMillis());
        task.setLastTriggered(new LocalDate());
        TaskRepository repository = new TaskRepository(appContext);
        repository.updateTask(task);
    }

    public static void setAsNotificationOnly(Context appContext, TaskModel task) {
        task.setIsAlarmSet(0);
        new NotificationHelper(appContext).showReminderNotification(task);
        TaskRepository repository = new TaskRepository(appContext);
        repository.updateTask(task);
    }
}
