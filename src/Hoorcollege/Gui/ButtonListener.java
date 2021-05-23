package Hoorcollege.Gui;

import Hoorcollege.Model.Person;

import java.util.ArrayList;
import java.util.EventListener;

public interface ButtonListener extends EventListener {
    void saveToDB();

    void loadFromDB();
}
