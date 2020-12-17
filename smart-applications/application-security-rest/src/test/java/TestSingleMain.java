import springfox.documentation.builders.PathSelectors;

import java.util.function.Predicate;

public class TestSingleMain {

    public static void main(String[] args) {
        String str = "xx/resourceserver/users/register";
        Predicate<String> selector = PathSelectors.regex(".*\\/resourceserver\\/.+");
        System.out.println(selector.test(str));
    }
}
