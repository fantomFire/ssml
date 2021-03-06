package zhonghuass.ssml.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;
import zhonghuass.ssml.http.ApiServer;
import zhonghuass.ssml.mvp.contract.PassWorldLoginContract;
import zhonghuass.ssml.mvp.model.appbean.PWLoginBean;


@ActivityScope
public class PassWorldLoginModel extends BaseModel implements PassWorldLoginContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public PassWorldLoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<PWLoginBean> pwtoLogin(String mPhone, String mPassworld) {
        return mRepositoryManager.obtainRetrofitService(ApiServer.class)
                .topwLogin(mPhone,mPassworld,"1");
    }
}