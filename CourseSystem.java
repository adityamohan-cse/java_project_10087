import java.util.Scanner;

class Person {
    protected String name;
    protected String id;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public void display() {
        System.out.println("Name : " + name);
        System.out.println("ID   : " + id);
    }
}

class Course {
    private String courseName;
    private String courseId;
    private String duration;

    private Student[] enrolledStudents = new Student[50];
    private int enrolledCount = 0;

    public Course(String courseName, String courseId, String duration) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.duration = duration;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void addStudent(Student student) {
        if (enrolledCount < enrolledStudents.length) {
            enrolledStudents[enrolledCount++] = student;
        }
    }

    public void displayCourse() {
        System.out.println("--------------------------------");
        System.out.println("Course Name : " + courseName);
        System.out.println("Course ID   : " + courseId);
        System.out.println("Duration    : " + duration);

        System.out.println("Enrolled Students:");

        if (enrolledCount == 0) {
            System.out.println("No Students Enrolled");
        } else {
            for (int i = 0; i < enrolledCount; i++) {
                System.out.println((i + 1) + ". "
                        + enrolledStudents[i].getName()
                        + " (" + enrolledStudents[i].getStudentId() + ")");
            }
        }
    }
}

class Student extends Person {

    private int progress;

    private Course[] courses = new Course[10];
    private int courseCount = 0;

    public Student(String name, String id) {
        super(name, id);
        progress = 0;
    }

    public String getStudentId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProgress() {
        return progress;
    }

    public void enroll(Course course) {

        for (int i = 0; i < courseCount; i++) {
            if (courses[i] == course) {
                System.out.println("Already Enrolled!");
                return;
            }
        }

        if (courseCount < courses.length) {
            courses[courseCount++] = course;
            course.addStudent(this);
            System.out.println("Enrollment Successful!");
        } else {
            System.out.println("Maximum Course Limit Reached!");
        }
    }

    public void updateProgress(int progress) {

        if (progress >= 0 && progress <= 100) {
            this.progress = progress;
            System.out.println("Progress Updated Successfully!");
        } else {
            System.out.println("Progress must be between 0 and 100.");
        }
    }

    public String getStatus() {
        if (progress >= 100)
            return "Completed";
        else
            return "In Progress";
    }

    @Override
    public void display() {

        System.out.println("--------------------------------");
        System.out.println("Student Name : " + name);
        System.out.println("Student ID   : " + id);

        System.out.println("Enrolled Courses:");

        if (courseCount == 0) {
            System.out.println("No Courses Enrolled");
        } else {
            for (int i = 0; i < courseCount; i++) {
                System.out.println((i + 1) + ". "
                        + courses[i].getCourseName());
            }
        }

        System.out.println("Progress : " + progress + "%");
        System.out.println("Status   : " + getStatus());
    }
}

public class CourseSystem {

    static Scanner sc = new Scanner(System.in);

    static Course[] courses = new Course[50];
    static Student[] students = new Student[50];

    static int courseCount = 0;
    static int studentCount = 0;

    public static void main(String[] args) {

        int choice = 0;

        do {

            try {

                System.out.println("\n======================================");
                System.out.println(" ONLINE COURSE MANAGEMENT SYSTEM ");
                System.out.println("======================================");
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

                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        addCourse();
                        break;

                    case 2:
                        addStudent();
                        break;

                    case 3:
                        enrollStudent();
                        break;

                    case 4:
                        updateProgress();
                        break;

                    case 5:
                        searchStudent();
                        break;

                    case 6:
                        searchCourse();
                        break;

                    case 7:
                        displayAllStudents();
                        break;

                    case 8:
                        displayAllCourses();
                        break;

                    case 9:
                        displayCompleteReport();
                        break;

                    case 10:
                        System.out.println("Thank You!");
                        System.out.println("Exiting System...");
                        break;

                    default:
                        System.out.println("Invalid Choice!");
                }

            } catch (Exception e) {
                System.out.println("Invalid Input! Please Try Again.");
            }

        } while (choice != 10);
    }

    public static void addCourse() {

        if (courseCount >= courses.length) {
            System.out.println("Course Storage Full!");
            return;
        }

        System.out.print("Enter Course Name : ");
        String name = sc.nextLine();

        System.out.print("Enter Course ID   : ");
        String id = sc.nextLine();

        System.out.print("Enter Duration    : ");
        String duration = sc.nextLine();

        courses[courseCount++] = new Course(name, id, duration);

        System.out.println("Course Added Successfully!");
    }

    public static void addStudent() {

        if (studentCount >= students.length) {
            System.out.println("Student Storage Full!");
            return;
        }

        System.out.print("Enter Student Name : ");
        String name = sc.nextLine();

        System.out.print("Enter Student ID   : ");
        String id = sc.nextLine();

        students[studentCount++] = new Student(name, id);

        System.out.println("Student Added Successfully!");
    }

    public static void enrollStudent() {

        System.out.print("Enter Student ID : ");
        String sid = sc.nextLine();

        System.out.print("Enter Course ID  : ");
        String cid = sc.nextLine();

        Student student = findStudent(sid);
        Course course = findCourse(cid);

        if (student != null && course != null) {
            student.enroll(course);
        } else {
            System.out.println("Student or Course Not Found!");
        }
    }

    public static void updateProgress() {

        System.out.print("Enter Student ID : ");
        String sid = sc.nextLine();

        Student student = findStudent(sid);

        if (student != null) {

            System.out.print("Enter Progress (0-100): ");
            int progress = Integer.parseInt(sc.nextLine());

            student.updateProgress(progress);

        } else {
            System.out.println("Student Not Found!");
        }
    }

    public static void searchStudent() {

        System.out.print("Enter Student ID : ");
        String sid = sc.nextLine();

        Student student = findStudent(sid);

        if (student != null) {
            student.display();
        } else {
            System.out.println("Student Not Found!");
        }
    }

    public static void searchCourse() {

        System.out.print("Enter Course ID : ");
        String cid = sc.nextLine();

        Course course = findCourse(cid);

        if (course != null) {
            course.displayCourse();
        } else {
            System.out.println("Course Not Found!");
        }
    }

    public static void displayAllStudents() {

        if (studentCount == 0) {
            System.out.println("No Students Available!");
            return;
        }

        for (int i = 0; i < studentCount; i++) {
            students[i].display();
        }
    }

