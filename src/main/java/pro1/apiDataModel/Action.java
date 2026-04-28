package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Action
{
    public String obsazeni;
    public String ucitIdno;

    @SerializedName("denZkr")
    public String den;
}
