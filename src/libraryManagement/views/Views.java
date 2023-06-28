package libraryManagement.views;

import libraryManagement.utils.Utils;

import java.util.*;

public class Views {
    public static void views() {
        String[] choices = {"Manage titles & books", "Manage readers", "Borrow & Return", "Exit"};
        while (true) {
            int choice = Utils.showChoices(List.of(choices));
            if (choice == 1) {
                manageTitlesAndBooks();
            } else if (choice == 2) {
                manageReaders();
            } else if (choice == 3) {
                borrowAndReturn();
            } else {
                break;
            }
        }
    }

    public static void manageTitlesAndBooks() {
        String[] choices = {"Add title", "Add book", "Delete title", "Delete book", "Search title", "Search book", "Return"};
        while (true) {
            int choice = Utils.showChoices(List.of(choices));
            if (choice == 1) {
                BookViews.addTitle();
            } else if (choice == 2) {
                BookViews.addBook();
            } else if (choice == 3) {
                BookViews.deleteTitle();
            } else if (choice == 4) {
                BookViews.deleteBook();
            } else if (choice == 5) {
                BookViews.searchTitle();
            } else if (choice == 6) {
                BookViews.searchBook();
            } else {
                break;
            }
        }
    }

    public static void manageReaders() {
        String[] choices = {"Add reader", "Delete reader", "Search reader", "Change reader's borrowing permission", "Return"};
        while (true) {
            int choice = Utils.showChoices(List.of(choices));
            if (choice == 1) {
                ReaderViews.addReader();
            } else if (choice == 2) {
                ReaderViews.deleteReader();
            } else if (choice == 3) {
                ReaderViews.searchReader();
            } else if (choice == 4) {
                ReaderViews.changeBookBorrowed();
            } else {
                break;
            }
        }
    }

    public static void borrowAndReturn() {
        String[] choices = {"Borrow book", "Return book", "Extend expire date", "Return"};
        while (true) {
            int choice = Utils.showChoices(choices);
            if (choice == 1) {
                BorrowAndReturnViews.borrowBook();
            } else if (choice == 2) {
                BorrowAndReturnViews.returnBook();
            } else if (choice == 3) {
                BorrowAndReturnViews.extendExpireDate();
            } else {
                break;
            }
        }
    }
}
