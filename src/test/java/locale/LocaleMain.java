package locale;

import javafx.scene.input.DataFormat;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LocaleMain {
    public static void main(String[] args){
        Locale locale = new Locale("ru","RU");
        Locale locale1 = Locale.getDefault();
        Locale locale2 = new Locale("de", "DE");

/*
        System.out.println(Locale.getDefault());
        System.out.println("Число");
        System.out.println(NumberFormat.getNumberInstance(locale).format(1000.01));
        System.out.println(NumberFormat.getNumberInstance(locale1).format(1000.01));
        System.out.println(NumberFormat.getNumberInstance(locale2).format(1000.01));

        System.out.println("Денежный формат");
        System.out.println(NumberFormat.getCurrencyInstance(locale).format(1000));
        System.out.println(NumberFormat.getCurrencyInstance(locale1).format(1000));
        System.out.println(NumberFormat.getCurrencyInstance(locale2).format(1000));
 */
        System.out.println("Дата конвертации в локали");
        System.out.println(DateFormat.getDateInstance(DateFormat.FULL, locale).format(new Date()));
        System.out.println(DateFormat.getDateInstance(DateFormat.FULL, locale1).format(new Date()));

        System.out.println("форматирование даты в заданный формат");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
        System.out.println(Calendar.getInstance().getTime());
        System.out.println(dateFormat.format(Calendar.getInstance().getTime()));

       /* Locale[] locales = Locale.getAvailableLocales();
        for (Locale loc: locales){
            System.out.println(loc);
        } */


    }
}
