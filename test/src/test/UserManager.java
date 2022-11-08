package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

class UserManager {
    private static UserManager instance = new UserManager();
    public static UserManager getInstance() {
    return instance;
    }

    private File file;

    private LinkedHashMap<String, User> users = new LinkedHashMap<String, User>();
    //LinkedHashMap 자료구조는 HashMap 자료구조에서 자동으로 섞이는거를 순서를 유지시켜 준다.
    //키, 값을 통해 저장되어 있는 정보들중 해당하는 값을 찾아낸다.

    //UserManager에서 입력받은 정볼르 data.csv로 뽑아내기
    public UserManager() {
        file = new File("C://Users//tjxog//OneDrive//바탕 화면/data.csv");
        if(file.exists()) {
            load();
        }
    }
    //data.csv 파일을 읽어와서 , ","로 split 함수로 문자열을 구분하고, 토큰에 저장한다. 
    //즉 한줄씩 통째로 읽어들임

    public void load() {
        try {
            var fr = new FileReader(file);
            var br = new BufferedReader(fr);
            String line = br.readLine();
            while((line = br.readLine()) != null) {
                String[] tokens = line.split(","); //,를 기준으로 문자열을 자른다

                User user = new User();
                user.studentName = tokens[0];
                user.studentNumber = tokens[1];
                user.grade = tokens[2];
                user.studentID = tokens[3];
                user.studentPW = tokens[4];
                user.phoneNumber = tokens[5];
                user.email = tokens[6];
                user.address = tokens[7];

                System.out.println(user.studentName);

                users.put(user.studentID, user);
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //저장한다.
    public void save() {
        // StudentName,StudentNumber,Grade,StudentID,StudentPW,PhoneNumber,Email,Address
        try {
            FileWriter fw = new FileWriter(file);
            fw.append("StudentName,StudentNumber,Grade,StudentID,StudentPW,PhoneNumber,Email,Address\n");

            for(var entry : users.entrySet()) {
                User user = entry.getValue();

                String[] tokens = new String[]{
                    user.studentName,
                    user.studentNumber,
                    user.grade,
                    user.studentID,
                    user.studentPW,
                    user.phoneNumber,
                    user.email,
                    user.address
                };

                fw.append(String.join(",", tokens) + "\n"); //한줄씩 구분하여 데이터 구분
            }
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //ID값을 찾고
    public User getUser(String studentID) {
        return users.get(studentID);
    }

    //키, 값을 통해 값 통째로 저장한다.
    public void createUser(User user) {
        users.put(user.studentID, user);
        save();
    }
}
