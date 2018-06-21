package com.byvoid.wanangushi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author melody
 * @date 2018/6/21
 */
public class StoryDetail extends Story{
    private static final long serialVersionUID = -2996259624749077860L;

    @SerializedName("list")
    private List<StoryContent> storyContentList = new ArrayList<>();

    public StoryDetail(){

    }

    public List<StoryContent> getStoryContentList() {
        return storyContentList;
    }
}
