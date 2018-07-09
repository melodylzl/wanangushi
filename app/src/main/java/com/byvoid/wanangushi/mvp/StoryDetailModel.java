package com.byvoid.wanangushi.mvp;

import android.util.SparseArray;

import com.byvoid.wanangushi.http.BaseCallBack;
import com.byvoid.wanangushi.http.HttpService;
import com.byvoid.wanangushi.model.Role;
import com.byvoid.wanangushi.model.StoryDetail;
import com.byvoid.wanangushi.model.StoryTalk;

import java.util.List;

/**
 * @author melody
 * @date 2018/6/21
 */
public class StoryDetailModel implements IStoryDetailModel{

    private StoryDetail mStoryDetail;
    private SparseArray<Role> mRoleSparseArray = new SparseArray<>();

    @Override
    public void getStoryDetail(int id, final IBaseCallBack.IGetDataCallBack<StoryDetail> callBack) {
        HttpService.getStoryDetail(id, new BaseCallBack<StoryDetail>() {
            @Override
            public void onSuccess(StoryDetail data, String msg) {
                handleRoleData(data);
                callBack.onSuccess(data,msg);
            }

            @Override
            public void onFail(String msg, int code) {
                callBack.onFail(msg,code);
            }
        });
    }

    @Override
    public StoryDetail getStoryDetailData() {
        return mStoryDetail;
    }

    private void handleRoleData(StoryDetail data){
        mStoryDetail = data;
        List<StoryTalk> storyTalkList = data.getStoryTalkList();
        List<Role> roleList = data.getRoleList();
        for (Role role : roleList){
            mRoleSparseArray.put(role.getId(),role);
        }
        for (StoryTalk storyTalk : storyTalkList){
            storyTalk.setRole(mRoleSparseArray.get(storyTalk.getRoleId()));
        }
    }

}
