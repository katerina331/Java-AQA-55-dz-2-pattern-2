package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import entities.RegistrationInfo;
import org.junit.jupiter.api.Test;
import utils.CreateUser;
import utils.DataGenerator;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class LoginTest {

    @Test
    void shouldTestActive() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo info = DataGenerator.Registration.generationInfo(true, "ru");
        CreateUser.CreateNewUser(info);
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        $("[id=root]").shouldHave(Condition.text("Личный кабинет"));
        $("[id=root] .icon_theme_alfa-on-white").shouldHave(Condition.visible);
    }

    @Test
    void shouldTestNotActive() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo info = DataGenerator.Registration.generationInfo(false, "ru");
        CreateUser.CreateNewUser(info);
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldHave(Condition.text("Пользователь заблокирован")).shouldHave(Condition.visible);
    }

    @Test
    void shouldTestEnLogin() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo info = DataGenerator.Registration.generationInfo(true, "en");
        CreateUser.CreateNewUser(info);
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        $("[id=root]").shouldHave(Condition.text("Личный кабинет"));
        $("[id=root] .icon_theme_alfa-on-white").shouldHave(Condition.visible);
    }

    @Test
    void shouldTestNonLogin() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo info = DataGenerator.Registration.generationInfo(true, "ru");
        CreateUser.CreateNewUser(info);
        $("[data-test-id=login] input").setValue("");
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=login] .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestNonPas() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo info = DataGenerator.Registration.generationInfo(true, "ru");
        CreateUser.CreateNewUser(info);
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue("");
        $("[data-test-id=action-login]").click();
        $("[data-test-id=password] .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestBadLogin() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo info = DataGenerator.Registration.generationInfo(true, "ru");
        CreateUser.CreateNewUser(info);
        $("[data-test-id=login] input").setValue("Иван");
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldHave(Condition.text("Неверно указан логин или пароль")).shouldHave(Condition.visible);
    }

    @Test
    void shouldTestBadPas() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo info = DataGenerator.Registration.generationInfo(true, "ru");
        CreateUser.CreateNewUser(info);
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue("girlm876ylk");
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldHave(Condition.text("Неверно указан логин или пароль")).shouldHave(Condition.visible);
    }
}
