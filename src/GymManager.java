import javafx.scene.layout.HBox;

public interface GymManager {
    public static final int maxMembers = 100;

    public abstract void addNewMember(DefaultMember defaultMember);
    public abstract void deleteMember(String membershipNumber);
    public abstract void printMember();
    public  abstract void sortItem();
    public  abstract  void saveFile();
    public  abstract  DefaultMember search(String text);
    public abstract HBox getGUI();
}
