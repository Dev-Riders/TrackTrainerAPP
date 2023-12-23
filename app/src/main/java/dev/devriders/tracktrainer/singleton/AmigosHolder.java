package dev.devriders.tracktrainer.singleton;

import java.util.List;
import dev.devriders.tracktrainer.models.Amigo;

public class AmigosHolder {
    private static AmigosHolder instance;
    private List<Amigo> listaAmigos;

    private AmigosHolder() {}

    public static synchronized AmigosHolder getInstance() {
        if (instance == null) {
            instance = new AmigosHolder();
        }
        return instance;
    }

    public List<Amigo> getListaAmigos() {
        return listaAmigos;
    }

    public void setListaAmigos(List<Amigo> listaAmigos) {
        this.listaAmigos = listaAmigos;
    }
}