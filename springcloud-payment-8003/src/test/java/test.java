import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * author biXia
 * create 2020-07-14-15:37
 */

public class test
{
    public static void main(String[] args)
    {

        test test = new test();
        test.run1(3);
    }

    /**
     * 阶乘
     * @param n
     */
    public void run1(int n ){
        int sum = 0;
        for (int i = 0; i <= n; i++)
        {
             sum = i*(i-1);

        }
        System.out.println(sum);
    }
}
