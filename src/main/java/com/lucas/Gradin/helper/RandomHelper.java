package com.lucas.Gradin.helper;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;

public class RandomHelper {

    protected static SecureRandom random = new SecureRandom();

    public static synchronized String getRandomHexString(int size) {
        StringBuffer sb = new StringBuffer();
        while (sb.length() < size) {
            sb.append(Integer.toHexString(random.nextInt()));
        }
        return sb.toString().substring(0, size);
    }

    public static synchronized String getToken(int size) {
        return Long.toString(Math.abs(random.nextLong()), size);
    }

    public static int getRandomInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static String generateDNI() {
        Random r = new Random();
        int num = r.nextInt((99999999 - 10000000) + 1) + 10000000;
        String dni = String.valueOf(num);
        char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        char letra = letras[num % 23];
        return dni + letra;
    }

    public static String generateEmail(String nombre, String apellido1){
        String[] services = {"@hotmail.com", "@outlook.com", "@gmail.com", "@yahoo.com", "@aol.com", "@gradin.com"};
        Random r = new Random();
        int index = r.nextInt(services.length);
        String email = nombre.toLowerCase() + "." + apellido1.toLowerCase() + services[index];
        return email;
    }

    public static LocalDateTime getRadomDateTime() {
        return RandomHelper.getRadomDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date getRadomDate() {
        GregorianCalendar gc = new GregorianCalendar();
        int year = getRandomInt(2010, 2019);
        gc.set(1, year);
        int dayOfYear = getRandomInt(1, gc.getActualMaximum(6));
        gc.set(6, dayOfYear);
        Date date = new Date(gc.getTimeInMillis());
        return date;
    }

    public static char getRadomChar() {
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');
        return Character.toUpperCase(c);
    }

    public static double getRadomDouble(int rangeMin, int rangeMax) {
        Random r = new Random();
        return rangeMin + (rangeMax - rangeMin) * r.nextDouble();
    }

    public static double getRadomDouble(double minValue, double maxValue) {
        return Math.round(ThreadLocalRandom.current().nextDouble(minValue, maxValue) * 100d) / 100d;
    }
}