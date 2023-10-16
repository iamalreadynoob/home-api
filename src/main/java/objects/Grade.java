package objects;

public class Grade
{

    private String name, status, midtermNote, finalNote, projects;

    public Grade(String name, String status, String midtermNote, String finalNote, String projects)
    {
        this.name = name;
        this.status = status;
        this.midtermNote = midtermNote;
        this.finalNote = finalNote;
        this.projects = projects;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}

    public String getMidtermNote() {return midtermNote;}

    public void setMidtermNote(String midtermNote) {this.midtermNote = midtermNote;}

    public String getFinalNote() {return finalNote;}

    public void setFinalNote(String finalNote) {this.finalNote = finalNote;}

    public String getProjects() {return projects;}

    public void setProjects(String projects) {this.projects = projects;}
}
