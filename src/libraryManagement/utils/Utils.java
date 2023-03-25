package libraryManagement.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static String generateId(int length) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder id = new StringBuilder(length);

        for(int i = 0; i < length; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            id.append(AlphaNumericString.charAt(index));
        }
        return id.toString();
    }

    public static Date convertStringToDate(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Wrong date format!");
        }
        return null;
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }

    public static Date calculateExpireDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 30);
        return c.getTime();
    }

    public static Date extendExpireDate(Date expireDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(expireDate);
        c.add(Calendar.DATE, 14);
        return c.getTime();
    }

    public static int subtractDate(Date expireDate, Date returnDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        long diff = returnDate.getTime() - expireDate.getTime();
        TimeUnit timeUnit = TimeUnit.DAYS;
        long different = timeUnit.convert(diff,TimeUnit.MILLISECONDS);
        return (int) different;
    }

    public static Date getDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }
}
