package pkg3d.main;

/**
 * Thanks to https://github.com/javatutorials101/Tutorials
 * I got the inspiration and much of the base engine code from this github
 * 
 * Also thanks to CodeNMore https://www.youtube.com/user/CodeNMore
 * Watching his videos helped me to create the structure of the program
 * 
 * @author asile
 * Starts the program
 */
public class Launcher {
    
    public static void main(String[] args){
        Main main = new Main();
        main.run(1/20.0);
    }
}
