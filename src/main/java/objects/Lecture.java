package objects;

public class Lecture
{

    private String code, name, day, from, to;

    public Lecture(String code, String name, String day, String from, String to)
    {
        this.code = code;
        this.name = name;
        this.day = day;
        this.from = from;
        this.to = to;
    }

    public String getCode() {return code;}

    public void setCode(String code) {this.code = code;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDay() {return day;}

    public void setDay(String day) {this.day = day;}

    public String getFrom() {return from;}

    public void setFrom(String from) {this.from = from;}

    public String getTo() {return to;}

    public void setTo(String to) {this.to = to;}
}
