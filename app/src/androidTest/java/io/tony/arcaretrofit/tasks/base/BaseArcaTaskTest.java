package io.tony.arcaretrofit.tasks.base;

import android.content.Context;
import android.net.Uri;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import io.pivotal.arca.service.Task;
import io.pivotal.arca.threading.Identifier;
import io.tony.arcaretrofit.ArcaRetrofitTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(ArcaRetrofitTestRunner.class)
public abstract class BaseArcaTaskTest<TYPE extends Task>{

    public TYPE task;
    public Context context;

    protected abstract TYPE instantiateTask();
    protected abstract Object getIdentifierObject();
    protected abstract Uri getUri();

    @Before
    public void setup() throws Exception {
        context = Robolectric.getShadowApplication().getApplicationContext();
        task = instantiateTask();
        task.setContext(context);
    }

    @Test
    public void testOnCreateIdentifier() throws Exception {
        final Identifier<Object> identifier = task.onCreateIdentifier();
        assertThat(identifier).isEqualTo(new Identifier<>(getIdentifierObject()));
    }

    @Test
    public abstract void testOnExecuteNetworkingSuccess() throws Exception;

    @Test
    public abstract void testOnExecuteNetworkingFailure() throws Exception;

    @Test
    public abstract void testOnExecuteProcessing() throws Exception;
}
