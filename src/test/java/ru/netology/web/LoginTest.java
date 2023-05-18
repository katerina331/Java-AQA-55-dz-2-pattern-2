package ru.netology.web;

import com.codeborne.selenide.Condition;
import entities.RegistrationInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.CreateUser;
import utils.DataGenerator;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class LoginTest {
    @BeforeEach
    public void Setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestActive() {
        RegistrationInfo info = DataGenerator.Registration.generationInfoVol("active", "ru");
        CreateUser.createNewUser(info);
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        $(".App_appContainer__3jRx1").shouldHave(Condition.text("Личный кабинет"));
        $(".App_appContainer__3jRx1 .icon_theme_alfa-on-white").shouldHave(Condition.visible);
    }

    @Test
    void shouldTestNotActive() {
        RegistrationInfo info = DataGenerator.Registration.generationInfoVol("blocked", "ru");
        CreateUser.createNewUser(info);
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldHave(Condition.text("Пользователь заблокирован")).shouldHave(Condition.visible);
    }

    @Test
    void shouldTestEnLogin() {
        RegistrationInfo info = DataGenerator.Registration.generationInfoVol("active", "en");
        CreateUser.createNewUser(info);
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        $(".App_appContainer__3jRx1").shouldHave(Condition.text("Личный кабинет"));
        $(".App_appContainer__3jRx1 .icon_theme_alfa-on-white").shouldHave(Condition.visible);
    }

    @Test
    void shouldTestNonLogin() {
        RegistrationInfo info = DataGenerator.Registration.generationInfoVol("active", "ru");
        CreateUser.createNewUser(info);
        $("[data-test-id=login] input").setValue("");
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=login] .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestNonPas() {
        RegistrationInfo info = DataGenerator.Registration.generationInfoVol("active", "ru");
        CreateUser.createNewUser(info);
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue("");
        $("[data-test-id=action-login]").click();
        $("[data-test-id=password] .input__sub").shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestBadLogin() {
        RegistrationInfo info = DataGenerator.Registration.generationInfoVol("active", "ru");
        CreateUser.createNewUser(info);
        $("[data-test-id=login] input").setValue(DataGenerator.Registration.generationInfoUnVol("active", "ru").getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldHave(Condition.text("Неверно указан логин или пароль")).shouldHave(Condition.visible);
    }

    @Test
    void shouldTestBadPas() {
        RegistrationInfo info = DataGenerator.Registration.generationInfoVol("active", "ru");
        CreateUser.createNewUser(info);
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(DataGenerator.Registration.generationInfoUnVol("active", "ru").getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldHave(Condition.text("Неверно указан логин или пароль")).shouldHave(Condition.visible);
    }
}
