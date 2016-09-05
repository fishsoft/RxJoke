package rx.dong.com.rxjoke.module;

import dagger.Module;
import dagger.Provides;
import rx.dong.com.rxjoke.ui.view.JokeView;

/**
 * auther：Morse
 * time：2016/9/5 10:23
 * Descripte：
 */
@Module
public class MainModule {

    private final JokeView jokeView;

    public MainModule(JokeView jokeView) {
        this.jokeView = jokeView;
    }

    @Provides
    JokeView provideJokeView() {
        return jokeView;
    }

}
