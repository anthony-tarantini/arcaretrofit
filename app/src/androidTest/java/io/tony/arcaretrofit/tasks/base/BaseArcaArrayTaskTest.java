package io.tony.arcaretrofit.tasks.base;

import android.content.ContentResolver;
import android.database.Cursor;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import java.util.List;

import io.pivotal.arca.service.Task;
import io.tony.arcaretrofit.ArcaRetrofitTestRunner;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(ArcaRetrofitTestRunner.class)
public abstract class BaseArcaArrayTaskTest<TASK extends Task, RESPONSE extends List> extends BaseArcaTaskTest<TASK> {

    protected MockWebServer server;

    protected abstract int getResponseLength();
    protected abstract String getSuccessResponse();
    protected abstract String getErrorResponse();

    @Before
    public void setup() throws Exception {
        super.setup();
        server = new MockWebServer();
        server.play();
    }

    @Test
    public void testOnExecuteNetworkingSuccess() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(getSuccessResponse()));
        final RESPONSE items = (RESPONSE) task.executeNetworking();
        assertThat(items.size()).isEqualTo(getResponseLength());
    }

    @Test
    public void testOnExecuteNetworkingFailure() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(500).setBody(getErrorResponse()));
        final RESPONSE items = (RESPONSE) task.executeNetworking();
        assertThat(items.size()).isEqualTo(getResponseLength());
    }

    @Test
    public void testOnExecuteProcessing() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody(getSuccessResponse()));
        final RESPONSE items = (RESPONSE) task.executeNetworking();
        task.executeProcessing(items);

        testResponse(items);
    }

    private void testResponse(final RESPONSE items) {
        final ContentResolver contentResolver = Robolectric.getShadowApplication().getContentResolver();
        final Cursor query = contentResolver.query(getUri(), null, null, null, null);

        assertThat(query.getCount()).isEqualTo(getResponseLength());
        query.close();
    }

    @After
    public void cleanup() throws Exception {
        server.shutdown();
    }
}
