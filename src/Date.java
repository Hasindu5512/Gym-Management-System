public class Date {

    private int day;
    private int month;
    private int year;


    public String getDate() {
        String date = String.valueOf(day) + "-" + String.valueOf(month) + "-" + String.valueOf(year);
        return date;
    }

    public void setYear(int year) {
        if (year < 2021 && year > 2000){
            this.year = year;
        } else {
            System.out.println("Invalid Year");
        }
    }

    public void setMonth(int month) {
        if (month <= 12 && month >= 1){
            this.month = month;
        }
    }

    public void setDay(int day) {
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (day<=31 && day>=1){
                    this.day = day;
                } else {
                    System.out.println("Invalid Date");
                }
                break;
            case 2:
                if (year%4==0){
                    if (day<=29 && day>=1){
                        this.day = day;
                    } else {
                        System.out.println("Invalid Date");
                    }
                } else {
                    if (day<=28 && day>=1){
                        this.day = day;
                    } else {
                        System.out.println("Invalid Date");
                    }
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (day<=30 && day>=1){
                    this.day = day;
                } else {
                    System.out.println("Invalid Date");
                }
        }
    }
}




