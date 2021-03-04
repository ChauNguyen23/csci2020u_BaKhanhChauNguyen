package sample;

public class StudentRecord {
    public String studID;
    public float assignment;
    public float midterm;
    public float finalExam;
    public double finalMark;
    public String letterGrade;
    public StudentRecord(String sid, float assignmentMark, float midtermMark, float finalExamMark) {
        this.studID = sid;
        this.assignment = assignmentMark;
        this.midterm = midtermMark;
        this.finalExam = finalExamMark;
        this.finalMark = this.assignment * 0.2 + this.midterm * 0.3 + this.finalExam * 0.5;
        if(this.finalMark>= 80 && this.finalMark<=100){
            this.letterGrade = "A";
        }
        else if(this.finalMark>= 70 && this.finalMark<=79){
            this.letterGrade = "B";
        }
        else if(this.finalMark>= 60 && this.finalMark<=69){
            this.letterGrade = "C";
        }
        else if(this.finalMark>= 50 && this.finalMark<=59){
            this.letterGrade = "D";
        }
        else if(this.finalMark>= 0 && this.finalMark<=49){
            this.letterGrade = "F";
        }
    }

    public String getStudID() {
        return this.studID;
    }

    public float getMidterm() { return this.midterm; }

    public float getAssignment() {
        return this.assignment;
    }

    public float getFinalExam() { return this.finalExam; }

    public double getFinalMark() { return this.finalMark; }

    public String getLetterGrade() { return this.letterGrade; }
}

