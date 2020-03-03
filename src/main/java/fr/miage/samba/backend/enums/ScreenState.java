package fr.miage.samba.backend.enums;

import fr.miage.samba.backend.Annotation.ScoreCoefficient;

import java.util.HashMap;
import java.util.Map;

public enum ScreenState implements MaterialProductState {

    BROKEN(0,"L'écran est cassé",0),

    LOW(1,"L'écran est tres rayé",10),

    MEDIUM(2,"L'écran est rayé",25),

    HIGH(3,"L'écran est en bon etat",50);

    private static final Map<Integer, ScreenState> LOOKUP = new HashMap<>();

    static {
        for (ScreenState e: values()) {
            LOOKUP.put(e.stateCode, e);
        }
    }
    private int stateCode;
    private String libelle;
    private int scoreCoef;

    ScreenState(int state,String libelle,int scoreCoef){
        this.stateCode = state;
        this.libelle = libelle;
        this.scoreCoef = scoreCoef;

    }

    public String getLibelle() {
        return libelle;
    }

    public int getStateCode() {
        return stateCode;
    }

    public static ScreenState getEnumFromStateCode(int stateCode) {
        return LOOKUP.get(stateCode);
    }

    public int getScoreCoef() {
        return scoreCoef;
    }

    public int getMaxScore() { return getEnumFromStateCode(values().length-1).getScoreCoef(); }
}
