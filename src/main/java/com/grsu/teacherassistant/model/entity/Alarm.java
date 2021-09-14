package com.grsu.teacherassistant.model.entity;

import com.grsu.teacherassistant.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.Base64;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Alarm extends AssistantEntity {

    private Boolean active;

    private Integer time;

    private byte[] sound;

    private double volume = 1.0;

    public String getSoundData() {
        if (sound != null && sound.length > 0) {
            return "data:audio/mpeg;base64," + new String(Base64.getEncoder().encode(sound));
        } else {
            return Constants.DEFAULT_ALARM_SOUND;
        }
    }
}
