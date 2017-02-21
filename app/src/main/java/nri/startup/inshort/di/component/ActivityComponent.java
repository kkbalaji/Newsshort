package nri.startup.inshort.di.component;


import dagger.Component;
import nri.startup.inshort.di.scope.ActivityScope;
import nri.startup.inshort.ui.activity.BaseActivity;
import nri.startup.inshort.ui.activity.MainActivity;
import nri.startup.inshort.ui.activity.TutorialActivity;

/**
 * Created by Krushnakant Solanki on 17-02-2017.
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent extends AppComponent {

    void inject(BaseActivity coreBaseActivity);

    void inject(TutorialActivity tutorialActivity);

    void inject(MainActivity mainActivity);


}
