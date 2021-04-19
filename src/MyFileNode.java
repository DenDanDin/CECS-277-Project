import java.io.File;
public class MyFileNode {
    File file;
    String filename;

    public MyFileNode(String filename){
        this.filename = filename;
        file = new File(filename);
    }

    public MyFileNode(String name, File f){
        filename = name;
        file = f;
    }

    public File getFile(){
        return file;
    }

    public String getFileName(){
        return filename;
    }

    public String toString(){
        if(file.getName().equals("")){
            return file.getPath();
        }
        return file.getName();
    }

    public boolean isDirectory(){
        return file.isDirectory();
    }

}
