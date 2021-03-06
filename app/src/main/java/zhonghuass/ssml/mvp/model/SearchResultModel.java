package zhonghuass.ssml.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import com.jess.arms.di.scope.ActivityScope;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import zhonghuass.ssml.http.ApiServer;
import zhonghuass.ssml.http.BaseResponse;
import zhonghuass.ssml.mvp.contract.SearchResultContract;
import zhonghuass.ssml.mvp.model.appbean.RecommendBean;


@ActivityScope
public class SearchResultModel extends BaseModel implements SearchResultContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public SearchResultModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResponse<List<RecommendBean>>> getSearchResult(String member_id, String member_type, int page) {
        return mRepositoryManager.obtainRetrofitService(ApiServer.class)
                .getSearchResultData(member_id,member_type,page+"");
    }
}