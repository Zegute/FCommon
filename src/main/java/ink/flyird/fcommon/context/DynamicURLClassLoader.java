package ink.flyird.fcommon.context;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class DynamicURLClassLoader extends URLClassLoader {
    public DynamicURLClassLoader(){
        super(new URL[0],ClassLoader.getSystemClassLoader());
    }

    public void addURL(URL url){
        super.addURL(url);
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        for (URL url:this.getURLs()){
            try {
                File f= new File(url.getFile());
                JarFile f2=new JarFile(f);
                ZipEntry e=f2.getEntry(name.replaceFirst("/",""));
                if(e==null){
                    continue;
                }
                return f2.getInputStream(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
