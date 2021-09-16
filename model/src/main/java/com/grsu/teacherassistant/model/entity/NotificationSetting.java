package com.grsu.teacherassistant.model.entity;

import com.grsu.teacherassistant.model.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Base64;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "NOTIFICATION_SETTING")
public class NotificationSetting extends AssistantEntity {

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    private Boolean active;

    private Integer data;

    private double volume;

    private byte[] sound;

    public String getSoundData() {
        if (sound != null && sound.length > 0) {
            return "data:audio/mpeg;base64," + new String(Base64.getEncoder().encode(sound));
        } else {
            return Constants.DEFAULT_NOTIFICATION_SOUND;
        }
    }
}
