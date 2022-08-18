package edu.school21.cinema.models;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Authentication {
    private String ip;
    private Date date;
    private Time time;

    public Authentication(String ip, Date date, Time time) {
        this.ip = ip;
        this.date = date;
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authentication that = (Authentication) o;
        return Objects.equals(ip, that.ip) && Objects.equals(date, that.date) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, date, time);
    }

    @Override
    public String toString() {
        return "AuthenticationData{" +
                "ip='" + ip + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
