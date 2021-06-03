import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class MyGymManager implements GymManager {
    ArrayList<DefaultMember> dataArrayList = new ArrayList();

    /**
     *         Check the space in system
     */
    @Override
    public void addNewMember(DefaultMember defaultMember){
        if(dataArrayList.size() <= maxMembers){
            dataArrayList.add(defaultMember);
        }
        else {
            System.out.println("There are not enough space. Try again.!");
        }
    }

    /**                deleting part
     *    deleting members using their membership number
     */
    @Override
    public void deleteMember(String membershipNumber) {
        int index = -1;

        for (int i=0; i<dataArrayList.size(); i++){
            if(membershipNumber.equalsIgnoreCase(dataArrayList.get(i).getMembershipNumber())){
                index = i;
            }
        }

        if (index == -1){
            System.out.println("Member is not fount");
        }
        else{
            //----->delete - member details<-----//
            System.out.println("\t\tMember Details");
            System.out.println("\tMembership Number :- " + dataArrayList.get(index).getMembershipNumber());
            System.out.println("\tMember Name :- " + dataArrayList.get(index).getName());
            System.out.println("\tMembership Date :- " + dataArrayList.get(index).getStartMembershipDate());

            if(dataArrayList.get(index) instanceof StudentMember){
                System.out.println("\tMember School :- " + ((StudentMember) dataArrayList.get(index)).getSchoolName());
                System.out.println("\tMember Type - Student Member");
            }
            else if(dataArrayList.get(index) instanceof Over60Member) {
                System.out.println("\tMember Age :- " + ((Over60Member) dataArrayList.get(index)).getAge());
                System.out.println("\tMember Type - Over 60 Member");
            }
            else{
                System.out.println("\tMember Type - Default Member");
            }
            System.out.println("\tDelete Successful");

            dataArrayList.remove(index);

            System.out.println("\t▪▪▪▪▪▪▪▪Delete is successful▪▪▪▪▪▪▪▪\n");
        }
    }

    /**
     *     printing members details in console
     */
    @Override
    public void printMember(){

        for(DefaultMember  defaultMember : dataArrayList){
            System.out.println("\n\tMembership Number :- " + defaultMember.getMembershipNumber());
            System.out.println("\tMember Name :- " + defaultMember.getName());
            System.out.println("\tMember weight :- " + defaultMember.getWeight());
            System.out.println("\tMember height :- " + defaultMember.getHeight());

            if(defaultMember instanceof StudentMember){
                System.out.println("\tMember School :- " + ((StudentMember) defaultMember).getSchoolName());
                System.out.println("\tMember grade in school :- " + ((StudentMember) defaultMember).getGradeInScl());
                System.out.println("\tMember contact number :- " + ((StudentMember) defaultMember).getContactNumberStudent());
                System.out.println("\tMember Type - Student Member");
            }
            else if(defaultMember instanceof Over60Member) {
                System.out.println("\tMember Age :- " + ((Over60Member) defaultMember).getAge());
                System.out.println("\tAny operations :- " + ((Over60Member) defaultMember).getAnyOperations());
                System.out.println("\tMember Type - Over 60 Member");
            }
            else{
                System.out.println("\tMember Type - Default Member");
            }
            System.out.println("\tMembership Date :- " + defaultMember.getStartMembershipDate() + "\n");
        }
    }


    /**               sorting
     *     sorting members details to the ascending order
     *     according to the "name"
     */
    @Override
    public void sortItem(){
        ArrayList<String> name = new ArrayList<>();

        for(int i=0; i<dataArrayList.size(); i++){
            name.add(i,dataArrayList.get(i).getName());
        }

        for(int i=0; i<dataArrayList.size()-1; i++){
            for (int j=0; j<dataArrayList.size()-1-i; j++){
                if (name.get(j).compareTo(name.get(j+1)) > 0){
                    String sortName = name.get(j);
                    name.set(j, name.get(j+1));
                    name.set((j+1),sortName);

                    DefaultMember sortMember = dataArrayList.get(j);
                    dataArrayList.set(j,dataArrayList.get(j+1));
                    dataArrayList.set((j+1), sortMember);
                }
            }
        }
        System.out.println("▪▪▪▪▪▪▪▪Sort is successful▪▪▪▪▪▪▪▪\n");
        printMember();
    }

    /**             save member details
     *         using file writing and bubble Sort
     */
    @Override
    public void saveFile(){
        try {
            FileWriter fileWriterGym = new FileWriter("Gym_Management.txt",true);
            BufferedWriter bufferedWriterGym = new BufferedWriter(fileWriterGym);
            for (int i=0; i<dataArrayList.size(); i++){
                bufferedWriterGym.write("\tMembership Number :- " + dataArrayList.get(i).getMembershipNumber() + "\n");
                bufferedWriterGym.write("\tMember name :- " + dataArrayList.get(i).getName() + "\n");

                if(dataArrayList.get(i) instanceof StudentMember){
                    bufferedWriterGym.write("\tMember School :- " + ((StudentMember) dataArrayList.get(i)).getSchoolName() + "\n");
                    bufferedWriterGym.write("\tMember Type - Student Member");
                }
                else if(dataArrayList.get(i) instanceof Over60Member) {
                    bufferedWriterGym.write("\tMember Age :- " + ((Over60Member) dataArrayList.get(i)).getAge() + "\n");
                    bufferedWriterGym.write("\tMember Type - Over 60 Member\n");
                }
                else{
                    bufferedWriterGym.write("\tMember Type - Default Member\n");
                }
                bufferedWriterGym.write("\tMember membership date :- " + dataArrayList.get(i).getStartMembershipDate() + "\n");

                bufferedWriterGym.newLine();
            }
            bufferedWriterGym.close();
            System.out.println("▪▪▪▪▪Save is successful▪▪▪▪▪\n");
        }
        catch (Exception error){
            System.out.println("Error! Try again.");

        }
    }

    public DefaultMember search(String text){
        DefaultMember defaultMember=null;
        for (int i=0; i<dataArrayList.size();i++){
            if (text.equalsIgnoreCase(dataArrayList.get(i).getMembershipNumber())){
                defaultMember = dataArrayList.get(i);
            }
        }
        return defaultMember;
    }

    /**
     *         get a list of members
     */
    public HBox getGUI(){
        VBox vBox = new VBox();
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();
        VBox vBox3 = new VBox();
        VBox vBox4 = new VBox();
        VBox vBox5 = new VBox();
        VBox vBox6 = new VBox();
        VBox vBox7 = new VBox();
        VBox vBox8 = new VBox();
        VBox vBox9 = new VBox();
        VBox vBox10 = new VBox();

        vBox.setSpacing(10);
        vBox1.setSpacing(10);
        vBox2.setSpacing(10);
        vBox3.setSpacing(10);
        vBox4.setSpacing(10);
        vBox5.setSpacing(10);
        vBox6.setSpacing(10);
        vBox7.setSpacing(10);
        vBox8.setSpacing(10);
        vBox9.setSpacing(10);
        vBox10.setSpacing(10);

        vBox.getChildren().add(new Label("Type"));
        vBox1.getChildren().add(new Label("Membership Number"));
        vBox2.getChildren().add(new Label("Name"));
        vBox3.getChildren().add(new Label("Start Membership Date"));
        vBox4.getChildren().add(new Label("Weight"));
        vBox5.getChildren().add(new Label("Height"));
        vBox6.getChildren().add(new Label("Age"));
        vBox7.getChildren().add(new Label("Operations"));
        vBox8.getChildren().add(new Label("School Name"));
        vBox9.getChildren().add(new Label("Grade"));
        vBox10.getChildren().add(new Label("Contact Number"));


        for (int i=0; i<dataArrayList.size(); i++){
            if (dataArrayList.get(i) instanceof StudentMember){
                Label label = new Label("Student");
                Label label1 = new Label(dataArrayList.get(i).getMembershipNumber());
                Label label2 = new Label(dataArrayList.get(i).getName());
                Label label3 = new Label(dataArrayList.get(i).getStartMembershipDate());
                Label label4 = new Label(String.valueOf(dataArrayList.get(i).getWeight()));
                Label label5 = new Label(String.valueOf(dataArrayList.get(i).getHeight()));
                Label label6 = new Label(((StudentMember) dataArrayList.get(i)).getSchoolName());
                Label label7 = new Label(String.valueOf(((StudentMember) dataArrayList.get(i)).getGradeInScl()));
                Label label8 = new Label(((StudentMember) dataArrayList.get(i)).getContactNumberStudent());

                vBox.getChildren().add(label);
                vBox1.getChildren().add(label1);
                vBox2.getChildren().add(label2);
                vBox3.getChildren().add(label3);
                vBox4.getChildren().add(label4);
                vBox5.getChildren().add(label5);
                vBox8.getChildren().add(label6);
                vBox9.getChildren().add(label7);
                vBox10.getChildren().add(label8);
            } else if (dataArrayList.get(i) instanceof Over60Member){
                Label label = new Label("Over 60");
                Label label1 = new Label(dataArrayList.get(i).getMembershipNumber());
                Label label2 = new Label(dataArrayList.get(i).getName());
                Label label3 = new Label(dataArrayList.get(i).getStartMembershipDate());
                Label label4 = new Label(String.valueOf(dataArrayList.get(i).getWeight()));
                Label label5 = new Label(String.valueOf(dataArrayList.get(i).getHeight()));
                Label label6 = new Label(String.valueOf(((Over60Member) dataArrayList.get(i)).getAge()));
                Label label7 = new Label(((Over60Member) dataArrayList.get(i)).getAnyOperations());


                vBox.getChildren().add(label);
                vBox1.getChildren().add(label1);
                vBox2.getChildren().add(label2);
                vBox3.getChildren().add(label3);
                vBox4.getChildren().add(label4);
                vBox5.getChildren().add(label5);
                vBox6.getChildren().add(label6);
                vBox7.getChildren().add(label7);
            } else {
                Label label = new Label("Default");
                Label label1 = new Label(dataArrayList.get(i).getMembershipNumber());
                Label label2 = new Label(dataArrayList.get(i).getName());
                Label label3 = new Label(dataArrayList.get(i).getStartMembershipDate());
                Label label4 = new Label(String.valueOf(dataArrayList.get(i).getWeight()));
                Label label5 = new Label(String.valueOf(dataArrayList.get(i).getHeight()));
                vBox.getChildren().add(label);
                vBox1.getChildren().add(label1);
                vBox2.getChildren().add(label2);
                vBox3.getChildren().add(label3);
                vBox4.getChildren().add(label4);
                vBox5.getChildren().add(label5);
            }
        }

        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBox, vBox1,vBox2,vBox3,vBox4,vBox5,vBox6,vBox7);
        hBox.setSpacing(15);

        return hBox;
    }
}
