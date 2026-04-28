package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class TeacherCourse
{
    @SerializedName("zkratka")
    public String code;

    @SerializedName("nazev")
    public String title;
}
