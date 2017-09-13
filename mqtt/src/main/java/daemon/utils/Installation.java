package daemon.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

/**
 * Created by sun_rise on 2016/4/20.
 */
public class Installation {
    private static final String INSTALLATION = "INSTALLATION";
    private static String sID = null;

    public synchronized static String getId(Context context) {
        if (sID == null) {
            File installation = Environment.getExternalStorageDirectory();
            installation = new File(installation, "/MyFiles");
            installation.mkdirs();// 创建MyFiles目录(可创建多级目录)
            installation = new File(Environment.getExternalStorageDirectory(), INSTALLATION);
            try {
                if (!installation.exists())
                    writeInstallationFile(installation);
                sID = readInstallationFile(installation);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return sID;
    }

    private static String readInstallationFile(File installation) throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes, "UTF-8");
    }

    private static void writeInstallationFile(File installation) throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String id = UUID.randomUUID().toString();
        out.write(id.getBytes());
        out.close();
    }

}
