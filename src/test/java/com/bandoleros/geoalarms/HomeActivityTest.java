package com.bandoleros.geoalarms;

import android.content.Intent;
import android.widget.TextView;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


@RunWith(RobolectricTestRunner.class)
public class HomeActivityTest {
    private HomeActivity activity;
    private TextView welcomeText;

    @Before
    public void setUp() throws Exception {
        activity = new HomeActivity();
        activity.onCreate(null);
        welcomeText = (TextView) activity.findViewById(R.id.welcome_text_view);
    }

    @Test
    public void shouldHaveATextThatSaysLetsWriteSomeCode() throws Exception {
        assertThat((String) welcomeText.getText(), equalTo("Now, write some code!"));
    }
}
