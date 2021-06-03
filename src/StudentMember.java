public class StudentMember extends DefaultMember {

        private String schoolName;
        private  int gradeInScl;
        private String contactNumberStudent;

        //---Getter part----------
        public String getSchoolName(){
                return schoolName;
        }

        public int getGradeInScl(){
                return gradeInScl;
        }

        public String getContactNumberStudent(){
                return contactNumberStudent;
        }


        //---Setter part----------
        public void setSchoolName(String newSetSchoolName, int newGradeInScl, String newcontactNumberStudent){
                schoolName = newSetSchoolName;

                if (newGradeInScl <= 13 && newGradeInScl >= 1) {
                        gradeInScl = newGradeInScl;
                } else {
                        System.out.println("Invalid Input.! Grade = 1 to 13 ");
                }


                contactNumberStudent = newcontactNumberStudent;

        }
}
