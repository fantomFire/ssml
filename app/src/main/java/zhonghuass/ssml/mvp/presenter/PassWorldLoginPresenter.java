package zhonghuass.ssml.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import zhonghuass.ssml.mvp.contract.PassWorldLoginContract;
import zhonghuass.ssml.mvp.model.appbean.LoginBean;
import zhonghuass.ssml.mvp.model.appbean.PWLoginBean;
import zhonghuass.ssml.utils.RxUtils;


@ActivityScope
public class PassWorldLoginPresenter extends BasePresenter<PassWorldLoginContract.Model, PassWorldLoginContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public PassWorldLoginPresenter(PassWorldLoginContract.Model model, PassWorldLoginContract.View rootView) {
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

    public void pwtoLogin(String mPhone, String mPassworld) {
        mModel.pwtoLogin(mPhone, mPassworld)
                .compose(RxUtils.applySchedulers(mRootView))
                .subscribe(new ErrorHandleSubscriber<PWLoginBean>(mErrorHandler) {

                    @Override
                    public void onNext(PWLoginBean loginBeanBaseResponse) {
                        mRootView.showMessage(loginBeanBaseResponse.msg);
                        String status = loginBeanBaseResponse.status;
                        if (status.equals("200")) {
                            mRootView.gotoActivity(loginBeanBaseResponse);
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                    }
                });
    }
}
