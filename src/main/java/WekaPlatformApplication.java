import config.WekaPlatformConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import resources.ClassifierResource;

public class WekaPlatformApplication extends Application<WekaPlatformConfiguration> {

    public static void main(String[] args) {
        try {
            new WekaPlatformApplication().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(final WekaPlatformConfiguration configuration,
                    final Environment environment) {

        environment.jersey().register(ClassifierResource.class);
    }
}
