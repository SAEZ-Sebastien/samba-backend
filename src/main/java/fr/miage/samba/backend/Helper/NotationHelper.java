package fr.miage.samba.backend.Helper;

import fr.miage.samba.backend.Annotation.ScoreCoefficient;
import fr.miage.samba.backend.constants.NotationConstants;
import fr.miage.samba.backend.enums.MaterialProductState;
import fr.miage.samba.backend.model.ProductDto;

import java.lang.reflect.Field;
import java.util.HashMap;

public abstract class NotationHelper {

    public static int getGlobalScoreOf(ProductDto.ProductSpecifications productSpecifications){
        HashMap<String,Double> map = new HashMap<String,Double>();
        map.put(NotationConstants.SCORE,0.00);
        map.put(NotationConstants.MAX_SCORE,0.00);

        try {
            getScoreOf(productSpecifications, map);
            double value = (map.get(NotationConstants.SCORE) / map.get(NotationConstants.MAX_SCORE)) *100;
            return  (int) value;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return -1;

    }

    private static double getScoreOf(Object object, HashMap<String, Double> map) throws IllegalAccessException {

        if (object == null) {
            return 0;
        }
        if(!(object instanceof String) && !object.getClass().isPrimitive()){
            for(Field f : object.getClass().getDeclaredFields()){
                f.setAccessible(true);
                if(f.getAnnotation(ScoreCoefficient.class) != null){
                    if(f.getType().isEnum()){
                        MaterialProductState enumObj = (MaterialProductState) f.get(object);
                        if(enumObj != null){
                            map.put(NotationConstants.MAX_SCORE, map.get(NotationConstants.MAX_SCORE) + enumObj.getMaxScore());
                            map.put(NotationConstants.SCORE, map.get(NotationConstants.SCORE) + enumObj.getScoreCoef());
                        }
                    } else if(f.getType().isArray()){
                        boolean[] screens = (boolean[]) f.get(object);
                        int screenCounter = 0;
                        for(boolean screen : screens){
                            if(screen){
                                screenCounter++;
                            }
                        }
                        int valuePerScreen = f.getAnnotation(ScoreCoefficient.class).value();

                        map.put(NotationConstants.MAX_SCORE, map.get(NotationConstants.MAX_SCORE) + (screens.length * valuePerScreen));
                        map.put(NotationConstants.SCORE, map.get(NotationConstants.SCORE) + (screenCounter * valuePerScreen));

                    }else if (f.getType().isPrimitive() && f.getType().equals(boolean.class)){
                        map.put(NotationConstants.MAX_SCORE, map.get(NotationConstants.MAX_SCORE) + f.getAnnotation(ScoreCoefficient.class).value());
                        if((boolean) f.get(object)){
                            map.put(NotationConstants.SCORE, map.get(NotationConstants.SCORE) + f.getAnnotation(ScoreCoefficient.class).value());
                        }
                    }
                }else if(!f.getType().isPrimitive()
                        && !f.getType().equals(String.class)
                        && !f.getType().isArray() && !f.getType().isEnum()
                        && !f.getName().startsWith("this$")){
                    getScoreOf(f.get(object),map);
                }
            }
        }
        return map.get(NotationConstants.SCORE);
    }
}
