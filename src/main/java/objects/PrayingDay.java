package objects;

public class PrayingDay
{

    String date, fajr, sunrise, dhuhr, asr, maghrib, isha;

    public PrayingDay(String date, String fajr, String sunrise, String dhuhr, String asr, String maghrib, String isha)
    {
        this.date = date;
        this.fajr = fajr;
        this.sunrise = sunrise;
        this.dhuhr = dhuhr;
        this.asr = asr;
        this.maghrib = maghrib;
        this.isha = isha;
    }

    public String getDate() {return date;}

    public void setDate(String date) {this.date = date;}

    public String getFajr() {return fajr;}

    public void setFajr(String fajr) {this.fajr = fajr;}

    public String getSunrise() {return sunrise;}

    public void setSunrise(String sunrise) {this.sunrise = sunrise;}

    public String getDhuhr() {return dhuhr;}

    public void setDhuhr(String dhuhr) {this.dhuhr = dhuhr;}

    public String getAsr() {return asr;}

    public void setAsr(String asr) {this.asr = asr;}

    public String getMaghrib() {return maghrib;}

    public void setMaghrib(String maghrib) {this.maghrib = maghrib;}

    public String getIsha() {return isha;}

    public void setIsha(String isha) {this.isha = isha;}

}
