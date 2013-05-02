package GUI;

import controller.SongController;

import java.util.ArrayList;
import java.util.List;

// A simple container for the Controller,
// List of other sessions and the
// IP to connect to
public class Session {

    public static SongController songController = new SongController();
    public static List<SessionListener> sessionListeners = new ArrayList<SessionListener>();
    public static String ipToConnectTo;

}
