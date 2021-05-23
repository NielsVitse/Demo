package Hoorcollege.Gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public interface ColorChange extends MouseMotionListener {

    @Override
    default void mouseDragged(MouseEvent e){

    };
}
