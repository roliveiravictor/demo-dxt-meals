package stonetree.com.meals.core;

import android.util.Log;

import java.util.concurrent.CountDownLatch;

import stonetree.com.meals.core.model.Error;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class CoreProviderTest {

    private final CountDownLatch signal = new CountDownLatch(1);

    public void assertServiceFailure(Error error) {
        final String errorCode = String.valueOf(error.getCode());
        boolean isErrorRule = errorCode.contains("50")
                || errorCode.contains("40")
                || "-1".equals(error.getCode());

        assertTrue("Service Unavailable - Code: " + error.getCode() + "Message: " + error.getMessage(), isErrorRule);
    }

    public void notifyResponse(){
        getSignal().countDown();
    }

    public void waitProviderResponse(){
        try {
            getSignal().await();
        } catch (InterruptedException e) {
            Log.e(CoreProviderTest.class.getName(), e.getMessage());
        }
    }

    public void executeCoreFailure(Error error){
        notifyResponse();
        assertServiceFailure(error);
    }

    public void executeCoreSuccess(Object responseModel){
        notifyResponse();
        assumeTrue("Response successful",responseModel != null);
    }


    public CountDownLatch getSignal() {
        return signal;
    }

}
