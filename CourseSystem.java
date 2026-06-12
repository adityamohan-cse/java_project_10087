
import java.util.Scanner;

class Course {
    private String courseName;
    private String courseId;
    private String duration;

    public Course(String courseName, String courseId, String duration) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.duration = duration;
    }

    public String getCourseName() { return courseName; }
    public String getCourseId() { return courseId; }

    public void displayCourse() {
        System.out.println("Course Name : " + courseName);
        System.out.println("Course ID   : " + courseId);
        System.out.println("Duration    : " + duration);
    }
}

class Student {
    private String studentName;
    private String studentId;
    private int progress;

    private Course[] enrolledCourses = new Course[5];
    private int courseCount = 0;

    public Student(String studentName, String studentId) {
        this.studentName = studentName;
        this.studentId = studentId;
    }

    public String getStudentId() { return studentId; }

    public void enroll(Course course) {
        if (courseCount < enrolledCourses.length) {
            enrolledCourses[courseCount++] = course;
            System.out.println("Enrollment Successful!");
        } else {
            System.out.println("Maximum Course Limit Reached!");
        }
    }

    public void updateProgress(int progress) {
        if (progress >= 0 && progress <= 100) {
            this.progress = progress;
        }
    }

    public void displayStudent() {
        System.out.println("Student Name : " + studentName);
        System.out.println("Student ID   : " + studentId);
        System.out.println("Progress     : " + progress + "%");

        System.out.println("Enrolled Courses:");
        if (courseCount == 0) {
            System.out.println("No Courses Enrolled");
        } else {
            for (int i = 0; i < courseCount; i++) {
                System.out.println((i + 1) + ". " + enrolledCourses[i].getCourseName());
            }
        }
    }
}

public class CourseSystem {

    static Course[] courses = new Course[20];
    static Student[] students = new Student[20];
    static int courseCount = 0;
    static int studentCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== ONLINE COURSE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Course");
            System.out.println("2. Add Student");
            System.out.println("3. Enroll Student");
            System.out.println("4. Update Progress");
            System.out.println("5. Search Student");
            System.out.println("6. Search Course");
            System.out.println("7. Display All Students");
            System.out.println("8. Display All Courses");
            System.out.println("9. Display Complete Report");
            System.out.println("10. Exit");
            System.out.print("Enter Choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                choice = 0;
            }

            switch (choice) {
                case 1:
                    System.out.print("Course Name: ");
                    String cname = sc.nextLine();
                    System.out.print("Course ID: ");
                    String cid = sc.nextLine();
                    System.out.print("Duration: ");
                    String dur = sc.nextLine();
                    courses[courseCount++] = new Course(cname, cid, dur);
                    break;

                case 2:
                    System.out.print("Student Name: ");
                    String sname = sc.nextLine();
                    System.out.print("Student ID: ");
                    String sid = sc.nextLine();
                    students[studentCount++] = new Student(sname, sid);
                    break;

                case 3:
                    System.out.print("Student ID: ");
                    String esid = sc.nextLine();
                    System.out.print("Course ID: ");
                    String ecid = sc.nextLine();

                    Student st = findStudent(esid);
                    Course co = findCourse(ecid);

                    if (st != null && co != null) st.enroll(co);
                    else System.out.println("Student/Course Not Found");
                    break;

                case 4:
                    System.out.print("Student ID: ");
                    String psid = sc.nextLine();
                    Student stu = findStudent(psid);

                    if (stu != null) {
                        System.out.print("Progress: ");
                        int p = Integer.parseInt(sc.nextLine());
                        stu.updateProgress(p);
                    }
                    break;

                case 5:
                    System.out.print("Student ID: ");
                    String ssid = sc.nextLine();
                    Student s = findStudent(ssid);
                    if (s != null) s.displayStudent();
                    else System.out.println("Student Not Found");
                    break;

                case 6:
                    System.out.print("Course ID: ");
                    String scid = sc.nextLine();
                    Course c = findCourse(scid);
                    if (c != null) c.displayCourse();
                    else System.out.println("Course Not Found");
                    break;

                case 7:
                    for (int i = 0; i < studentCount; i++) {
                        students[i].displayStudent();
                        System.out.println();
                    }
                    break;

                case 8:
                    for (int i = 0; i < courseCount; i++) {
                        courses[i].displayCourse();
                        System.out.println();
                    }
                    break;

                case 9:
                    System.out.println("===== COMPLETE REPORT =====");
                    for (int i = 0; i < studentCount; i++) {
                        students[i].displayStudent();
                        System.out.println("---------------------");
                    }
                    break;

                case 10:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 10);

        sc.close();
    }

    static Student findStudent(String id) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentId().equalsIgnoreCase(id))
                return students[i];
        }
        return null;
    }

    static Course findCourse(String id) {
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].getCourseId().equalsIgnoreCase(id))
                return courses[i];
        }
        return null;
    }
}
