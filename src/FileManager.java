import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.awt.Desktop;

public class FileManager {

    public static void main(String args[]){
        File[] files = File.listRoots();
        for(File path: files){
            System.out.println(path);
        }
        App app = new App();
        app.go();

        //File file = new File("C:\\");
//        File file = new File("C:\\Users\\Daniel\\Desktop\\CSULB\\Spring 2021");
//        File[] files;
//        files = file.listFiles();
//        for(int i = 0; i < files.length; i++){
//            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//            DecimalFormat dformat = new DecimalFormat("#,###");
//            if(files[i].isDirectory()){
//                System.out.println("Directory:" + files[i].getAbsolutePath() + " Date:" + formatter.format(files[i].lastModified())
//                + " Size:" + dformat.format(files[i].length()));
//            }
//            else{
//                System.out.println("File:" + files[i].getAbsolutePath() + " Date:" + formatter.format(files[i].lastModified())
//                + " Size:" + dformat.format(files[i].length()));
//            }
//        }
//        System.out.println("\n\n\n");
//        File[] fromDirect;  // from a folder, you can get the subfolder and its contents.
//        fromDirect = files[0].listFiles();  //files[0] is Directory CECS 229
//        for(int i = 0; i < fromDirect.length; i++){
//            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//            DecimalFormat dformat = new DecimalFormat("#,###");
//            if(fromDirect[i].isDirectory()){
//                System.out.println("Directory:" + fromDirect[i].getAbsolutePath() + " Date:" + formatter.format(fromDirect[i].lastModified())
//                        + " Size:" + dformat.format(fromDirect[i].length()));
//            }
//            else{
//                System.out.println("File:" + fromDirect[i].getAbsolutePath() + " Date:" + formatter.format(fromDirect[i].lastModified())
//                        + " Size:" + dformat.format(fromDirect[i].length()));
//            }
//        }
//        Desktop desktop = Desktop.getDesktop();
//        try{
//            desktop.open(new File(fromDirect[3].getAbsolutePath()));
//        } catch(IOException ex){
//            System.out.println(ex.toString());
//        }
    }
}
