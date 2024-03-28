package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ModalFormComponent {

    private final SelenideElement modalWindow = $(".modal-dialog"),
                                  titleInput =  $("#example-modal-sizes-title-lg"),
                                  tableInput = $(".table-responsive");

    public void checkModalAppear(String key, String value){
        modalWindow.should(appear);
        titleInput.shouldHave(text("Thanks for submitting the form"));
        tableInput.$(byText(key)).parent()
                .shouldHave(text(value));

    }
    public void checkNoModalAppear(){
        modalWindow.shouldNot(appear);
        titleInput.shouldNotBe(visible);
    }
}
