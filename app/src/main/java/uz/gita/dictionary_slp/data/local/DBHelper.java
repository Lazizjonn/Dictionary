package uz.gita.dictionary_slp.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {
    private final Context mContext;
    private SQLiteDatabase mDatabase;
    private final String mDataBaseName;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.disableWriteAheadLogging();
    }

    @Override
    public synchronized void close() {
        if (mDatabase != null)
            mDatabase.close();
        SQLiteDatabase.releaseMemory();
        super.close();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        closeDatabase();
    }

    public SQLiteDatabase database() {
        return mDatabase;
    }
    public void openDatabase() {
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        String dbPath = mContext.getDatabasePath(mDataBaseName).getPath();
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }
    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    protected DBHelper(Context context, final String mDataBaseName, int version) {
        super(context, mDataBaseName, null, version);
        this.mDataBaseName = mDataBaseName;
        mContext = context;
        if (!isExist()) {
            getReadableDatabase();
            if (!is_openDatabase()) return;
        }
        openDatabase();
    }

    private boolean isExist() {
        File database = mContext.getApplicationContext().getDatabasePath(mDataBaseName);
        return database.exists();
    }
    private boolean is_openDatabase() {
        try {
            long oldTime = System.currentTimeMillis();
            InputStream inputStream = mContext.getAssets().open(mDataBaseName);
            String outFileName = mContext.getDatabasePath(mDataBaseName).getAbsolutePath();
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.d("TTT", "spend time : " + (System.currentTimeMillis() - oldTime));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}