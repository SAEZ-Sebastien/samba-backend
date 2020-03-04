package fr.miage.samba.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.internal.NotNull;
import fr.miage.samba.backend.Annotation.ScoreCoefficient;
import fr.miage.samba.backend.constants.ScoreConstants;
import fr.miage.samba.backend.constants.SpecificationsConstants;
import fr.miage.samba.backend.enums.ScreenState;
import fr.miage.samba.backend.enums.ShellState;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


/*


brand, model, storage, os, os version
etat ecran
etat coque
description

 */
@Data
@Document(collection = "product")
public class ProductDto {

    @Id
    private  String id;
    private String title;
    private String description;
    private double price;
    private String sellerId;
    private int score;
    private ProductSpecifications productSpecifications;

    public ProductDto(){

    }

    public String getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id.toHexString();
    }

    @Data
    public class ProductSpecifications{
/*
        {"bluetooth": false, "button": {"power": false, "volumeDown": true, "volumeSwitch": false, "volumeUp": true}, "camera": {"back": true, "front": true}, "gps": true, "multitouch": true, "screen": [], "torch": true, "vibrator": true, "wifi": true}
        */

        private String brand;
        private String os;
        private String osVersion;
        private String storage;
        private String model;
        @NotNull
        @NotBlank
        private String deviceId;

        @ScoreCoefficient(value = ScoreConstants.LOW_MEDIUM_PRIORITY)
        private boolean bluetooth;

        private CameraSpecifications cameraSpecifications;
        private ButtonsSpecifications buttonsSpecifications;

        @ScoreCoefficient(value = ScoreConstants.LOW_MEDIUM_PRIORITY)
        private boolean gps;

        @ScoreCoefficient(value = ScoreConstants.MEDIUM_PRIORITY)
        private boolean multitouch;

        @ScoreCoefficient(value = ScoreConstants.HIGH_PRIORITY) //Per tiles
        private boolean[] screen = new boolean[SpecificationsConstants.TILES];

        @ScoreCoefficient(value = ScoreConstants.LOW_PRORITY)
        private boolean torch;

        @ScoreCoefficient(value = ScoreConstants.LOW_PRORITY)
        private boolean vibrator;

        @ScoreCoefficient(value = ScoreConstants.LOW_MEDIUM_PRIORITY)
        private boolean wifi;

        @ScoreCoefficient
        private ScreenState screenState;

        @ScoreCoefficient
        private ShellState shellState;


        public ProductSpecifications(){
        }

        @Data
        private class CameraSpecifications{
            @ScoreCoefficient(value = ScoreConstants.HIGH_PRIORITY)
            private boolean back;
            @ScoreCoefficient(value = ScoreConstants.HIGH_PRIORITY)
            private boolean front;
            public CameraSpecifications(){

            }
        }

        @Data
        private class ButtonsSpecifications{
            @ScoreCoefficient(value = ScoreConstants.VERY_HIGH_PRIORITY)
            private boolean power;
            @ScoreCoefficient(value = ScoreConstants.HIGH_PRIORITY)
            private boolean volumeDown;
            @ScoreCoefficient(value = ScoreConstants.VERY_LOW_PRIORITY)
            private boolean volumeSwitch;
            @ScoreCoefficient(value = ScoreConstants.HIGH_PRIORITY)
            private boolean volumeUp;

            public ButtonsSpecifications(){

            }

        }
    }

}
