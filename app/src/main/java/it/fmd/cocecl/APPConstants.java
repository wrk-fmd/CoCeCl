package it.fmd.cocecl;

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

    //REGISTER / LOGIN URL
    public static String URL_LOGIN = "http://192.168.43.224/android_json_login/getmeth.php";

    public static String URL_REGISTER = "http://192.168.43.224/android_json_login/postmeth.php";


    //GCM URL
        // Php Application URL to store Reg ID created
        static final String APP_SERVER_URL = "http://192.168.43.224:9000/gcm/gcm.php?shareRegId=true";
        // Google Project Number
        static final String GOOGLE_PROJ_ID = "000000000000";
        // Message Key
        public static final String MSG_KEY = "m";

    //MLS URL
    public static final String MLS_DOMAIN = "http://192.168.43.224/JSON/index.php";

    //Configuration file link
    public static final String cocecl_config = "";

    // Phone numbers
    //MLS
    public static final String mlsmain = "+ 43 660 123 456 78";
    public static final String mlsbv = "+ 43 660 123 456 78";
    public static final String mlsdispo = "+ 43 660 123 456 78";
    //EL
    public static final String hio = "+ 43 660 123 456 78";

    //EMERGENCY
    public static final String polnbr = "133";
    public static final String rdnbr = "144";
    public static final String fdnbr = "122";
    public static final String wrnbr = "";
    public static final String mrdnbr = "";
    public static final String oebbnbr = "";


    }
