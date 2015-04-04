package appsbyrishabh.loginsample;

/**
 * Created by Rishabh-PC on 4/4/2015.
 */


        import java.util.HashMap;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.content.SharedPreferences.Editor;
        import android.widget.Toast;

public class SessionManager {

    SharedPreferences pref;

    Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AppLogin";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_PASSWORD = "password";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public boolean createLoginSession(String name, String password) {

        if (pref.getString(name + "user", "new").equals(name) && (pref.getString(password + "Password","new").equals(password))) {

            editor.putBoolean("loggedin", true);

            editor.putString(KEY_NAME, name);

            // Storing email in pref
            editor.putString(KEY_PASSWORD, password);

            // commit changes
            editor.commit();
            return true;
        }
        return false;
    }


    public boolean register(String name, String password){
        if(pref.getString(name+"user","new").equals("new")) {
            editor.putString(name + "user", name);
            editor.putString(password + "Password", password);
            editor.commit();   // commit the values
            return true;
        }

        return false;

    }




    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){


            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }



    public void logoutUser(){

        editor.putBoolean("loggedin",false);
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        LoginActivity.login = 0;
        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean("loggedin", false);
    }
}
