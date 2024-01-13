package id.ac.binus.videostreamingapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateConvert {
    public static String getTimeAgo(String dateStr) {
        try
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            
            Date video = format.parse(dateStr);
            Date current = new Date();
            
            long seconds = TimeUnit.MILLISECONDS.toSeconds(current.getTime() - video.getTime());
            long minutes = TimeUnit.MILLISECONDS.toMinutes(current.getTime() - video.getTime());
            long hours = TimeUnit.MILLISECONDS.toHours(current.getTime() - video.getTime());
            long days = TimeUnit.MILLISECONDS.toDays(current.getTime() - video.getTime());

            if(seconds < 60) {
                if(seconds > 1){
                    return seconds + " seconds ago";
                }
                else{
                    return seconds + " second ago";
                }
            }
            else if(minutes < 60) {
                if(minutes > 1){
                    return minutes + " minutes ago";
                }
                else{
                    return minutes + " minute ago";
                }
            }
            else if(hours < 24) {
                if(hours > 1){
                    return hours + " hours ago";
                }
                else{
                    return hours + " hour ago";
                }
            }
            else if(days < 30) {
                if(days > 1){
                    return days + " days ago";
                }
                else{
                    return days + " day ago";
                }
            }
            else if(days < 365) {
                long months = days / 30;
                if(months > 1){
                    return months + " months ago";
                }
                else{
                    return months + " month ago";
                }
            }
            else {
                long years = days / 365;
                if(years > 1){
                    return years + " years ago";
                }
                else{
                    return years + " year ago";
                }
            }
        }
        catch (Exception j){
            j.printStackTrace();
            return dateStr;
        }
    }
}