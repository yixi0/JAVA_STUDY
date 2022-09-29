import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * @BelongsProject: OJ
 * @BelongsPackage: PACKAGE_NAME
 * @Author: Origami
 * @Date: 2022/9/7 9:50
 */
public class DateTest {
    public static void main(String[] args) {
        String date = "2002/1/1";
        int idx = 0;
        while (!Objects.equals(date.charAt(idx), '/')) {
            ++idx;
        }
        int year = Integer.parseInt(date.substring(0, idx));
        int idx1 = idx++;
        System.out.println("idx1 = " + idx1);
        while (!Objects.equals(date.charAt(idx), '/')) {
            ++idx;
        }
        System.out.println("idx = " + idx);
        int month = Integer.parseInt(date.substring(idx1 + 1, idx));
        int day = Integer.parseInt(date.substring(idx + 1));
        System.out.printf("%d %d %d", year, month, day);
    }
}
