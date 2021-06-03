public class Over60Member extends DefaultMember {

    private int age;
    private  String anyOperations;


    //---Getter part----------
    public int getAge(){
        return age;
    }

    public String getAnyOperations(){
        return anyOperations;
    }


    //---Setter part----------
    public void setAge(int newAge, String newAnyOperation){

        //---->Member Age Getter<----//
        if (newAge > 60){
            age = newAge;
        } else {
            System.out.println("Your age shout be over 60");
        }

        //---->Any Operations Getter<----//
        anyOperations = newAnyOperation;
    }
}
