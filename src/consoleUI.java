import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Scanner;

public class consoleUI extends Application {
    MyGymManager myGymManager = new MyGymManager();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start (Stage primaryStage) throws Exception{

        Scanner sc = new Scanner(System.in);

        loop1:
        while (true){
            System.out.println("\n\t❌▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪❌");
            System.out.println("🟢▪▪▪▪▪WELCOME TO GYM MANAGEMENT SYSTEM▪▪▪▪▪▪🟢");
            System.out.println("\t❌▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪❌\n");

            System.out.println("For add new member press \'A\' or \'a\'");
            System.out.println("For delete a member press \'D\' or \'d\'");
            System.out.println("For print members list press \'P\' or \'p\'");
            System.out.println("For sort the items press \'S\' or \'s\'");          //--> User Options <--//
            System.out.println("For write or save press \'W\' or \'w\'");
            System.out.println("For search press \'T\' or \'t\'");
            System.out.println("For view all in GUI press \'G\' or \'g\'");
            System.out.println("For Exit the program press \'Q\' or \'q\'");
            System.out.println("----------------------------------------------------");

            System.out.print("\nPlease..! Enter your choice :- ");

            String input = sc.next();
            switch (input){
                case "A":
                case "a":
                    loop2:
                    while (true) {
                        System.out.println("\nPress \'1\' for Default Member");
                        System.out.println("Press \'2\' for Student Member");
                        System.out.println("Press \'3\' for Over 60 Member");       //-->Adding Part<--//
                        System.out.println("Press \'B\' or \'b\' for back");
                        System.out.println("----------------------------------------------------");

                        System.out.print("\nPlease..! Enter your choice :- ");

                        String inputAdd = sc.next();
                        switch (inputAdd) {
                            case "1":
                                addDefaultMember();
                                break;
                            case "2":
                                addStudentMember();
                                break;
                            case "3":
                                addOver60Member();
                                break;
                            case "B":
                            case "b":
                                break loop2;
                            default:
                                System.out.println("Please, Enter the valid input.");
                        }
                    }
                    break;
                case "D":
                case "d":
                    System.out.print("Enter the membership number to delete :- ");
                    String membershipNumber = sc.next();
                    myGymManager.deleteMember(membershipNumber);
                    break;
                case "P":
                case "p":
                    myGymManager.printMember();
                    break;
                case "S":
                case "s":
                    myGymManager.sortItem();
                    break;
                case "W":
                case "w":
                    myGymManager.saveFile();
                    break;
                case "T":
                case "t":
                    search();
                    break;
                case "G":
                case "g":
                    viewAllInGUI();
                case "Q":
                case "q":
                    System.out.println("\nYou are exiting the program..!");
                    System.out.println("Thank You..!");
                    break loop1;
                default:
                    System.out.println("Please, Enter the valid input.\n");
            }
        }
    }

    //---->view all members in GUI<----//
    private void viewAllInGUI() {
        Stage stage = new Stage();
        HBox vBox = myGymManager.getGUI();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vBox);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);

        stage.setScene(new Scene(borderPane, 1000, 600));
        stage.showAndWait();

    }

    //---->GUI for search member<----//
    private void search() {
        Stage stgHome = new Stage();
        stgHome.setTitle("Gym Management System");

        Label lblTitle = new Label("Gym Management\n \t System");
        lblTitle.setFont(new Font("Goudy Stout", 22));
        lblTitle.setLayoutX(32);
        lblTitle.setLayoutY(40);
        lblTitle.setStyle("-fx-background-color: #263238; -fx-border-width: 11px; -fx-border-color:  #778ca3; -fx-text-fill: aliceblue;-fx-pref-height: 115; -fx-pref-width: 548;-fx-alignment: center");

        Pane  paneHome = new Pane();
        paneHome.setStyle("-fx-background-color: #d1d8e0");

        TextField txtSearch = new TextField();
        txtSearch.setPromptText("Enter member number");
        txtSearch.setFocusTraversable(false);

        txtSearch.setLayoutY(222);
        txtSearch.setLayoutX(84);
        txtSearch.setStyle("-fx-pref-width: 235px;-fx-pref-height: 45px ;-fx-font-weight: bold; -fx-font-size: 18px");

        Button btnSearch = new Button("Search");
        btnSearch.setStyle("-fx-pref-width:96;-fx-pref-height:45;-fx-font-weight: bold;-fx-background-color: #006064;-fx-text-fill: #FFFFFF;-fx-background-radius: 20px;-fx-border-color: aliceblue;-fx-border-radius: 20;-fx-border-width: 2;-fx-font-size: 20px ");
        btnSearch.setLayoutY(222);
        btnSearch.setLayoutX(410);
        btnSearch.setOnAction(event -> {
            if (txtSearch.getText().isEmpty()){
                Alert alertEmptySpace = new Alert(Alert.AlertType.ERROR,"Please.! Input membership number",ButtonType.OK);
                alertEmptySpace.show();
            }                             /**---> Fixed empty text field error <---*/
            else {
                try{/**
                        output of the search member number
                    */
                    VBox vBox1 = new VBox();
                    vBox1.setLayoutX(53);
                    vBox1.setLayoutY(146);
                    vBox1.setStyle("-fx-background-color: #dfe4ea; -fx-pref-width: 307; -fx-pref-height: 216; -fx-font-weight: bold; -fx-font-size: 18px; -fx-spacing: 3px ");

                    DefaultMember defaultMember = myGymManager.search(txtSearch.getText());
                    Label label1 = new Label("Member number\t: " + defaultMember.getMembershipNumber());
                    Label label2 = new Label("Member name\t: " + defaultMember.getName());
                    Label label3 = new Label("Membership date\t: " + defaultMember.getStartMembershipDate());
                    Label label4 = new Label("Member Height\t: " + defaultMember.getHeight());
                    Label label5 = new Label("Member Weight\t: " + defaultMember.getWeight());

                    vBox1.getChildren().addAll(label1,label2,label3,label4,label5);

                    if(defaultMember instanceof StudentMember){
                        Label label6 = new Label("Member school\t: " + ((StudentMember) defaultMember).getSchoolName());
                        Label label7 = new Label("Contact Number\t: " + ((StudentMember) defaultMember).getContactNumberStudent());

                        vBox1.getChildren().addAll(label6,label7);
                    }
                    else if(defaultMember instanceof Over60Member) {
                        Label label8 = new Label("Member age\t: " + ((Over60Member)defaultMember).getAge());
                        Label label9 = new Label("Any operation\t: " + ((Over60Member)defaultMember).getAnyOperations());

                        vBox1.getChildren().addAll(label8,label9);
                    }

                    Stage stgOut = new Stage();
                    stgOut.setTitle("Member Details");
                    stgOut.initOwner(stgHome);
                    Label lbwTitleResult = new Label("\uD83D\uDD30 Member Details \uD83D\uDD30");
                    lbwTitleResult.setLayoutX(-8);
                    lbwTitleResult.setLayoutY(32);
                    lbwTitleResult.setStyle("-fx-background-color:  #263238; -fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: aliceblue;-fx-alignment: center; -fx-pref-height: 76; -fx-pref-width: 423");

                    Button btnOk = new Button("\uD83C\uDD97");
                    btnOk.setLayoutX(179);
                    btnOk.setLayoutY(384);
                    btnOk.setStyle("-fx-background-color:  #C5CAE9; -fx-border-color: black; -fx-font-size: 20px;-fx-pref-width: 54px;-fx-pref-height: 46px");
                    btnOk.setOnAction(event1 -> {
                        txtSearch.clear();
                        stgOut.close();
                    });

                    Pane paneOut = new Pane();
                    paneOut.setStyle("-fx-background-color: #d1d8e0");
                    paneOut.getChildren().addAll(vBox1,lbwTitleResult,btnOk);
                    stgOut.setScene(new Scene(paneOut, 400,418));
                    stgOut.setResizable(false);
                    stgOut.show();
                }
                catch (Exception Error){
                    Alert alertStringError = new Alert(Alert.AlertType.ERROR,"Some is worng. Please, Enter correct member number",ButtonType.OK);
                    txtSearch.clear();
                    alertStringError.show();
                }
            }
        });

        Button btnExit = new Button("Exit");
        btnExit.setLayoutX(515);
        btnExit.setLayoutY(350);
        btnExit.setStyle("-fx-background-color:  #E53935; -fx-text-fill: aliceblue;-fx-font-size: 14px;-fx-pref-height: 30px; -fx-pref-width: 65px;-fx-border-color: #6D214F");
        btnExit.setOnAction(event -> {
            stgHome.close();
        });

        paneHome.getChildren().addAll(lblTitle,txtSearch,btnSearch,btnExit);

        stgHome.setScene(new Scene(paneHome, 600, 400));
        stgHome.setResizable(false);
        stgHome.close();
        stgHome.showAndWait();
    }

    /**
     *  Add inputs for default member
     */
    private void addDefaultMember() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n \t\t-Default Member-");

        System.out.print("\tEnter membership number :- ");
        String membershipNumber = sc.nextLine();

        System.out.print("\tEnter member name :- ");
        String memberName = sc.nextLine();

        System.out.print("\tEnter member weight :- ");
        int memberWeight = sc.nextInt();

        System.out.print("\tEnter member height :- ");
        int memberHeight = sc.nextInt();

        System.out.println("\tEnter membership date");
        System.out.print("\t  Enter the year :- ");
        int year = sc.nextInt();

        System.out.print("\t  Enter the month :- ");
        int month = sc.nextInt();

        System.out.print("\t  Enter the day :- ");
        int day = sc.nextInt();

        DefaultMember defaultMember = new DefaultMember();
        defaultMember.setmemberDefault(membershipNumber,memberName,memberWeight,memberHeight);
        defaultMember.setStartMembershipDate(year,month,day);

        myGymManager.addNewMember(defaultMember);
    }

    /**
     *  Add inputs for student member
     */
    private void addStudentMember() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n \t\t-Student Member-");

        System.out.print("\tEnter membership number :- ");
        String membershipNumber = sc.nextLine();

        System.out.print("\tEnter member name :- ");
        String memberName = sc.nextLine();

        System.out.print("\tEnter the school name :- ");
        String memberSchoolName = sc.nextLine();

        System.out.print("\tEnter grade in school :- ");
        int memberGradeInScl = sc.nextInt();

        System.out.print("\tEnter the contact number :- ");
        String memberContactNumber = sc.next();

        System.out.print("\tEnter member weight :- ");
        int memberWeight = sc.nextInt();

        System.out.print("\tEnter member height :- ");
        int memberHeight = sc.nextInt();

        System.out.println("\tEnter membership date");
        System.out.print("\t  Enter the year :- ");
        int year = sc.nextInt();

        System.out.print("\t  Enter the month :- ");
        int month = sc.nextInt();

        System.out.print("\t  Enter the day :- ");
        int day = sc.nextInt();

        StudentMember studentMember = new StudentMember();
        studentMember.setmemberDefault(membershipNumber,memberName,memberWeight,memberHeight);
        studentMember.setStartMembershipDate(year,month,day);
        studentMember.setSchoolName(memberSchoolName,memberGradeInScl,memberContactNumber);

        myGymManager.addNewMember(studentMember);
    }

    /**
     *  Add inputs for over 60 member
     */
    private void addOver60Member() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);

                System.out.println("\n \t\t-Over 60 Member-");

                System.out.print("\tEnter membership number :- ");
                String membershipNumber = sc.nextLine();

                System.out.print("\tEnter member name :- ");
                String memberName = sc.nextLine();

                System.out.print("\tEnter the age :- ");
                int age = sc.nextInt();

                System.out.print("\tEnter member weight (kg) :- ");
                int memberWeight = sc.nextInt();

                System.out.print("\tEnter member height (cm) :- ");
                int memberHeight = sc.nextInt();

                System.out.print("\tDid you do any operations? (Yes/No) :- ");
                String anyOperations = sc.next();

                System.out.println("\tEnter the membership date");
                System.out.print("\t  Enter the year :- ");
                int year = sc.nextInt();

                System.out.print("\t  Enter the month :- ");
                int month = sc.nextInt();

                System.out.print("\t  Enter the day :- ");
                int day = sc.nextInt();

                Over60Member over60Member = new Over60Member();
                over60Member.setmemberDefault(membershipNumber, memberName, memberWeight, memberHeight);
                over60Member.setStartMembershipDate(year, month, day);
                over60Member.setAge(age, anyOperations);

                myGymManager.addNewMember(over60Member);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input try again..!");
            }
        }
    }
}
