package com.owenlejeune.preffered;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4ClassRunner.class)
public class PreferredTest {

    @Mock
    SharedPreferences mockPreferences;

    @Mock
    Context mockContext;

    @Mock
    Preferred.Builder mockBuilder;

    @Before
    public void setup() {
        when(mockContext.getSharedPreferences(any(), any())).thenReturn(mockPreferences);
        mockBuilder.setContext(mockContext).build();
    }

}
