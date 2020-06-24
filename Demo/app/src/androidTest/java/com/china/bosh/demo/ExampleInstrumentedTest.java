package com.china.bosh.demo;

import android.content.Context;
import android.graphics.Color;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.china.bosh.demo.demo", appContext.getPackageName());
    }

    @Test
    public void testParseColor() {
        long beforeTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            Color.parseColor("#003fb0");
        }
        long afterTime = System.currentTimeMillis();
        System.out.println("用时（ms）：" + (afterTime - beforeTime));
    }
}
