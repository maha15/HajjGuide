package com.example.maheen.projectsmd;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by maheen on 5/1/2017.
 */

public class HajjGuideContract {

    public static final String AUTHORITY = "com.example.maheen.projectsmd";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // Define the possible paths for accessing data in this contract
    // This is the path for the "tasks" directory
    public static final String PATH_TASKS = "items";


    public static final class Umrah implements BaseColumns {

        // TaskEntry content URI = base content URI + path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();


        // Task table and column names
        public static final String TABLE_NAME = "umrah";
        public static final String KEY_STEPNAME= "stepname";
        public static final String KEY_DESCRIPTION = "description";
        public static final String KEY_IMAGENAME = "imagename";

    }

    public static final class Hajj implements BaseColumns {

        // TaskEntry content URI = base content URI + path
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();


        // Task table and column names
        public static final String TABLE_NAME = "hajj";
        public static final String KEY_STEPNAME= "stepname";
        public static final String KEY_DESCRIPTION = "description";
        public static final String KEY_IMAGENAME = "imagename";

    }
}
