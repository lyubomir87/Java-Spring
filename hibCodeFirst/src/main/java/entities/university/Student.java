package entities.university;

import entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends BaseEntity {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private double averageGrade;
    private String attendance;
    private Set<Course>courses;

    public Student() {
    }
@Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
@Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
@Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
@Column(name = "average_grade")
    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }
@Column(name = "attendance")
    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
    @ManyToMany(
            cascade = {CascadeType.ALL}
    )
    @JoinTable(
            name = "students_courses",
            joinColumns = {@JoinColumn(
                    name = "student_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "course_id"
            )}
    )
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
