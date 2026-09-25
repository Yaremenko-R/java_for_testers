package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase{

    public static Iterator<Object[]> mantis() {
        List<Object[]> list = new ArrayList<>();
        String username = "user115";
        String password = "password";
        list.add(new Object[] {username, password});
        return list.iterator();
    }

    @ParameterizedTest
    @MethodSource("mantis")
    void canRegisterUser(String username, String password) {
        var email = String.format("%s@localhost", username);
        app.jamesCli().addUser(email, password);
        app.registration().startRegistr(username, email);
        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            String url = text.substring(matcher.start(), matcher.end());
            app.registration().finishRegistr(url, password);
        }
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
