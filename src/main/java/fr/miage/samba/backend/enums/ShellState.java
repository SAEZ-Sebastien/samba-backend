package fr.miage.samba.backend.enums;

import java.util.HashMap;
import java.util.Map;

public enum ShellState implements MaterialProductState{

    BROKEN(0,"La coque est cassée",0),

    LOW(1,"La coque est tres rayée",10),

    MEDIUM(2,"La coque est rayée",15),

    HIGH(3,"La coque est en bonne etat",20);

    private static final Map<Integer, ShellState> LOOKUP = new HashMap<>();

    static {
        for (ShellState e: values()) {
            LOOKUP.put(e.stateCode, e);
        }
    }

    private int stateCode;
    private String libelle;
    private int scoreCoef;

    private ShellState(int stateCode,String libelle, int scoreCoef){
        this.stateCode = stateCode;
        this.libelle = libelle;
        this.scoreCoef = scoreCoef;

    }
    public String getLibelle() {
        return libelle;
    }

    public int getStateCode() {
        return stateCode;
    }

    public static ShellState getEnumFromStateCode(int stateCode) {
        return LOOKUP.get(stateCode);
    }


    public int getScoreCoef() {
        return scoreCoef;
    }

    public int getMaxScore() { return getEnumFromStateCode(values().length-1).getScoreCoef(); }

}
