package Hoorcollege.Gui;

import java.util.EventListener;

public interface PrefsListener extends EventListener {
    public void preferencesSet(String user, String pass, int port);
}
