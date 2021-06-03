public class DefaultMember{

    private String membershipNumber;
    private String name;
    private String startMembershipDate;
    private int weight;
    private int height;

    //---Getter part----------
    public String getMembershipNumber() {
        return membershipNumber;
    }

    public String getName() {
        return name;
    }

    public String getStartMembershipDate() {
        return startMembershipDate;
    }

    public int getWeight (){
        return weight;
    }

    public int getHeight(){
        return height;
    }

    //---Setter part----------
    public void setmemberDefault(String newMembershipNumber, String newName, int newWeight, int newHeight) {
        membershipNumber = newMembershipNumber;
        name = newName;
        if (newHeight > 0){
            height = newHeight;

        } else {
            System.out.println("Invalid Height");
        }

        if (newWeight > 0){
            weight = newWeight;

        } else {
            System.out.println("Invalid Weight");
        }
    }

    public void setStartMembershipDate(int year, int month, int day) {
        Date date = new Date();
        date.setYear(year);
        date.setMonth(month);
        date.setDay(day);
        startMembershipDate = date.getDate();
    }
}

