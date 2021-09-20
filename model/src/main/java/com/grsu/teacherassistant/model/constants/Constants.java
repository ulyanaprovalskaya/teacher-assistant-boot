package com.grsu.teacherassistant.model.constants;

public final class Constants {
    //SERIAL
    public final static String SERIAL_READER_INFO_PREFIX = "RFID Reader";
    public final static String SERIAL_CARD_UID_PREFIX = "Card UID:";
    public final static String SERIAL_GET_INFO = "get info";
    public final static String SERIAL_SET_START = "set start";
    public final static String SERIAL_STATUS_OK = "status: ok";
    public final static String SERIAL_STATUS_OL = "status: ol";
    public final static String SERIAL_STATUS_ER = "status: er";
    public final static String SERIAL_STATUS_EL = "status: el";
    public final static String SERIAL_STATUS_DB = "status: db";

    //BEANS
    public final static String GROUPS_DELIMITER = ",<br/>";
    public static final String MARK_DELIMETER = "/";

    //REGISTRATION TYPE
    public final static String REGISTRATION_TYPE_MANUAL = "MANUAL";
    public final static String REGISTRATION_TYPE_AUTOMATIC = "AUTOMATIC";

    //LESSON SKIP
    public final static String TOTAL_SKIP = "total.skip";

    //NOTE TYPES
    public static final String STUDENT_LESSON = "STUDENT_LESSON";
    public static final String STUDENT = "STUDENT";
    public static final String LESSON = "LESSON";

    //LESSON TYPES
    public static final String OTHER = "OTHER";
    public static final String ATTESTATION = "ATTESTATION";

    //ALARM
    public static final String DEFAULT_ALARM_SOUND = "/resources/audio/default_alarm.mp3";

    //NOTIFICATION
    public static final String DEFAULT_NOTIFICATION_SOUND = "/resources/audio/default_notification.mp3";

    public static final String NOTIFICATION_SETTINGS_DIALOG_NAME = "notificationSettingsDialog";

    //BACKUP_REASON
    public static final String BACKUP_BEFORE_DESTROY = "OnExit";
    public static final String BACKUP_BEFORE_STREAM_CREATION = "Stream";
}
