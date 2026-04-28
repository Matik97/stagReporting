package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActionsList
{
    public Action[] rozvrhovaAkce;
    @SerializedName("rozvrhovaAkce")
    public List<Action> items;
}
