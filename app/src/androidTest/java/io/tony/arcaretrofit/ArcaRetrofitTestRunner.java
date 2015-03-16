package io.tony.arcaretrofit;

import org.junit.runners.model.InitializationError;
import org.robolectric.AndroidManifest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.res.Fs;
import org.robolectric.res.FsFile;

import java.io.File;
import java.io.IOException;

public class ArcaRetrofitTestRunner extends RobolectricTestRunner{

    public ArcaRetrofitTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest createAppManifest(FsFile manifestFile, FsFile resDir, FsFile assetsDir) {
        String file = "AndroidManifest.xml";
        try {
            final String current = new File(".").getCanonicalPath();
            if (!current.endsWith("app")) {
                file = "app/" + file;
            }
        } catch (IOException exc){
            exc.printStackTrace();
        }
        FsFile fsFile = Fs.newFile(new File("."));
        FsFile customManifest = fsFile.join(file);
        FsFile appBaseDir = customManifest.getParent();
        return new NoApplicationAndroidManifest(customManifest, appBaseDir.join("res"), appBaseDir.join("assets"));
    }
}
