package rx.dong.com.rxjoke.compl;

import com.orhanobut.logger.Logger;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.dong.com.rxjoke.api.JokeApi;
import rx.dong.com.rxjoke.api.RxService;
import rx.dong.com.rxjoke.model.ContentlistEntity;
import rx.dong.com.rxjoke.model.JokeEntity;
import rx.dong.com.rxjoke.ui.view.JokeView;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * auther：Morse
 * time：2016/9/5 10:09
 * Descripte：
 */
public class JokePresenterCompl {

    private JokeView mMvpView;

    @Inject
    public JokePresenterCompl(JokeView jokeView) {
        mMvpView = jokeView;
    }

    public void loadList(final int page) {
        RxService.createApi(JokeApi.class)
                .getJoke(page)
                .subscribeOn(Schedulers.io())
                .map(new Func1<JokeEntity, List<ContentlistEntity>>() {
                    @Override
                    public List<ContentlistEntity> call(JokeEntity jokeEntity) {
                        return jokeEntity.getShowapi_res_body().getContentlist();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ContentlistEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.getMessage());
                        mMvpView.showError(null, null);
                    }

                    @Override
                    public void onNext(List<ContentlistEntity> contentlistEntities) {
                        if (page == 1) {
                            mMvpView.refresh(contentlistEntities);
                        } else {
                            mMvpView.loadMore(contentlistEntities);
                        }
                    }
                });

    }
}
