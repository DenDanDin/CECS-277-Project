import java.io.File;
public class MyFileNode {
    File file;
    String filename;

    /**
     * Constructor for MyFileNode
     * @param filename - the file name.
     */
    public MyFileNode(String filename){
        this.filename = filename;
        this.file = new File(filename);
    }

    /**
     * Constructor for MyFileNode
     * @param name - the name of the file.
     * @param f - the file.
     */
    public MyFileNode(String name, File f){
        this.filename = name;
        this.file = f;
    }

    /**
     * Returns the file of MyFileNode.
     * @return the file.
     */
    public File getFile(){
        return file;
    }

    /**
     * Returns the boolean value of if MyFileNode contains
     * a subdirectory.
     * @return true iff the file contains a subdirectory
     * @return false otherwise.
     */
    public boolean hasSubDirectory(){
        if (file.isDirectory() != false) {
            File[] files = file.listFiles();
            if(files == null){
                return false;
            }
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the file name of MyFileNode.
     * @return the file name.
     */
    public String getFileName(){
        return filename;
    }

    /**
     * toString for MyFileNode.
     * @return the Path or the Name.
     */
    public String toString(){
        if(file.getName().equals("")){
            return file.getPath();
        }
        return file.getName();
    }

    /**
     * Returns the boolean value of if a
     * the file of MyFileNode is a Directory.
     * @return true iff the file is a directory.
     * @return false otherwise.
     */
    public boolean isDirectory(){
        return file.isDirectory();
    }

}
