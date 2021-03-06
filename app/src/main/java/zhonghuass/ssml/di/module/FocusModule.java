package zhonghuass.ssml.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import zhonghuass.ssml.mvp.contract.FocusContract;
import zhonghuass.ssml.mvp.model.FocusModel;


@Module
public class FocusModule {
    private FocusContract.View view;

    /**
     * 构建FocusModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public FocusModule(FocusContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    FocusContract.View provideFocusView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    FocusContract.Model provideFocusModel(FocusModel model) {
        return model;
    }
}