package helpers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DataProvider {

    public static Stream<Arguments> providerFindLaptopInCatalog(){
        return Stream.of(
                Arguments.of("Каталог", "Электроника", "Ноутбуки")
                //Arguments.of("Oткрытие", "Банк Открытие: кредит наличными, ипотека, кредитные и ...", "EUR")
        );
    }

//    public static Stream<Arguments> provideCheckingMoneyList(){
//        List<String> money = new ArrayList<>();
//        money.add("USD");
//        money.add("EUR");
//        return Stream.of(
//                Arguments.of("Oткрытие", "Банк Открытие: кредит наличными, ипотека, кредитные и ...", money)
//        );
//    }
}
