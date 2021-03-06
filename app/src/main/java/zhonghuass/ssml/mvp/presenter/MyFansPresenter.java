package zhonghuass.ssml.mvp.presenter;

import android.app.Application;

import com.jess.arms.integration.AppManager;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import zhonghuass.ssml.http.BaseResponse;
import zhonghuass.ssml.mvp.contract.MyFansContract;
import zhonghuass.ssml.mvp.model.appbean.ConcernFansBean;
import zhonghuass.ssml.utils.RxUtils;


@ActivityScope
public class MyFansPresenter extends BasePresenter<MyFansContract.Model, MyFansContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public MyFansPresenter(MyFansContract.Model model, MyFansContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void getMyFansDate(String mId, String mType, int page) {
        mModel.getMyFansData(mId, mType, page)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse<List<ConcernFansBean>>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse<List<ConcernFansBean>> listBaseResponse) {
                        if (listBaseResponse.isSuccess()) {
                            mRootView.showData(listBaseResponse.getData());
                        } else if (listBaseResponse.getStatus().equals("201")) {
                            List<ConcernFansBean> mList = new ArrayList<>();
                            mRootView.showData(mList);
                        } else {
                            mRootView.showMessage(listBaseResponse.getMessage());
                        }
                    }
                });
    }

    public void toCancelConcern(String mId, String mType, String member_id, String member_type) {
        mModel.toCancelConcern(mId, mType, member_id, member_type)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (baseResponse.isSuccess()) {
                            mRootView.showCancelSuccess(baseResponse.getMessage());
                        } else {
                            mRootView.showMessage(baseResponse.getMessage());
                        }
                    }
                });

    }

    public void toConcern(String mId, String mType, String member_id, String member_type) {
        mModel.toConcern(mId, mType, member_id, member_type)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<BaseResponse>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        if (baseResponse.isSuccess()) {
                            mRootView.showConcernSuccess(baseResponse.getMessage());
                        } else {
                            mRootView.showMessage(baseResponse.getMessage());
                        }
                    }
                });
    }
}
