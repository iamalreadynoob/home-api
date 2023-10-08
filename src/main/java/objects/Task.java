package objects;

public class Task
{

    private String name, task, deadline, status, tag, by;

    public Task(String name, String task, String deadline, String status, String tag, String by)
    {
        this.name = name;
        this.task = task;
        this.deadline = deadline;
        this.status = status;
        this.tag = tag;
        this.by = by;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getTask() {return task;}

    public void setTask(String task) {this.task = task;}

    public String getDeadline() {return deadline;}

    public void setDeadline(String deadline) {this.deadline = deadline;}

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}

    public String getTag() {return tag;}

    public void setTag(String tag) {this.tag = tag;}

    public String getBy() {return by;}

    public void setBy(String by) {this.by = by;}
}
