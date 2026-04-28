package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeacherCoursesList
{
    @SerializedName("zkratka")
    public String code;

    @SerializedName("nazev")
    public String title;

    @SerializedName("predmetUcitele")
    public List<TeacherCourse> items;
}
