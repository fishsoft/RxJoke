package rx.dong.com.rxjoke.component;

import dagger.Component;
import rx.dong.com.rxjoke.module.MainModule;
import rx.dong.com.rxjoke.ui.activity.MainActivity;

/**
 * auther：Morse
 * time：2016/9/5 10:22
 * Descripte：
 */
@Component(modules = MainModule.class)
public interface MainComponent {
    public void inject(MainActivity activity);
}
