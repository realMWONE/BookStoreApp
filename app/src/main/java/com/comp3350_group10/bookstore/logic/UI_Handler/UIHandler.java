package com.comp3350_group10.bookstore.logic.UI_Handler;

public interface UIHandler {
    void SearchButtonPressed(String keyword);

    void LoginButtonPressed();

    void LogoutButtonPressed();

    void UserSettingsButtonPressed();

    void IncrementStock();

    void DecrementStock();

    void SetStock();
}
