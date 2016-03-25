package it.fmd.cocecl;

import java.net.URL;

public class APPConstants {

    //JSON Node Names general
    public static final String TAG_MESSAGE = "Message";
    public static final String TAG_USERNAME = "Username";
    public static final String TAG_PASSWORD = "Password";
    public static final String TAG_FIRST_NAME = "FirstName";
    public static final String TAG_LAST_NAME = "LastName";
    public static final String TAG_DNR = "DNr";
    public static final String TAG_EMAIL = "Email";

    //JSON Node Names INCIDENT specific
    public static final String TAG_INCIDENT = "incident";
    public static final String TAG_TASK = "task";
    public static final String TAG_BO = "bo";
    public static final String TAG_AO = "ao";
    public static final String TAG_STATUS = "status";
    public static final String TAG_INFO = "info";
    public static final String TAG_CALLER = "brfr";
    public static final String TAG_UNIT = "unit";
    public static final String TAG_EVENTAMB = "ambulanz";
    public static final String TAG_EMERGENCY = "blue";
    public static final String TAG_DEPARTMENT_AO = "department_ao";
    public static final String TAG_TRANSPORT_NR = "tnr";

    //MLS URL
    public static final String MLS_DOMAIN = "http://www.google.com";
    //Configuration file link
    public static final String cocecl_config = "";
    // Phone numbers
    //MLS
    public static final String mlsmain = "+ 43 660 123 456 78";
    public static final String mlsbv = "+ 43 660 123 456 78";
    public static final String mlsdispo = "+ 43 660 123 456 78";
    //EL
    public static final String hio = "+ 43 660 123 456 78";
    //EMERGENCY Nbrs
    public static final String polnbr = "133";
    public static final String rdnbr = "144";
    public static final String fdnbr = "122";
    public static final String wrnbr = "";
    public static final String mrdnbr = "";
    public static final String oebbnbr = "";


    //GCM URL
    // Php Application URL to store Reg ID created
    static final String APP_SERVER_URL = "http://192.168.43.224:9000/gcm/gcm.php?shareRegId=true";
    // Google Project Number
    static final String GOOGLE_PROJ_ID = "000000000000";
    // Message Key
    public static final String MSG_KEY = "m";
    //REGISTER / LOGIN URL
    public static String URL_LOGIN = "http://10.0.2.2/loginJSON/postjson.php";
    public static String URL_REGISTER = "http://10.0.2.2/loginJSON/getjson.php";

    // MAPS API

    //PLACES API
    public static final String PLACES_API_KEY = "mykey";


    // GCM 3.0

    // flag to identify whether to show single line
    // or multi line text in push notification tray
    public static boolean appendNotificationMessages = true;

    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // type of push messages
    public static final int PUSH_TYPE_CHATROOM = 1;
    public static final int PUSH_TYPE_USER = 2;

    // id to handle the notification in the notification try
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

}
