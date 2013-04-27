package GUI;

import controller.SongController;

import java.util.ArrayList;
import java.util.List;

public class Session {

    public static SongController songController = new SongController();
    public static List<SessionListener> sessionListeners = new ArrayList<SessionListener>();

}
