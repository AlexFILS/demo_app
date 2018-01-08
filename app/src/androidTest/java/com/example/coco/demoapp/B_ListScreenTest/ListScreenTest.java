package com.example.coco.demoapp.B_ListScreenTest;

import android.support.test.runner.AndroidJUnit4;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;

import com.example.coco.demoapp.Activities.ListScreen;
import com.example.coco.demoapp.Activities.MainActivity;
import com.example.coco.demoapp.R;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.contrib.RecyclerViewActions;

import static com.example.coco.demoapp.R.*;
import static com.example.coco.demoapp.R.id.recycle_view;
import static java.util.EnumSet.allOf;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by alexandrumihai1 on 23/11/2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class ListScreenTest {

    @Rule
    public ActivityTestRule<ListScreen> mActivityRule =
            new ActivityTestRule<ListScreen>(ListScreen.class);

    @Test
    public void test1_checkItemsDisplayed() {
        onView(withId(id.btnActive)).check(matches(isDisplayed()));
        onView(withId(id.btnFinished)).check(matches(isDisplayed()));
        onView(withId(id.btnActivenonpm)).check(matches(isDisplayed()));
        onView(withId(recycle_view)).check(new RecyclerViewItemCountAssertion(7));
    }

    @Test
    public void test2_checkItemCount() {
        onView(withId(id.btnActive)).perform(click());
        for (int i = 0; i < 30; i++) {
            if (i % 3 == 0) {
                onView(withId(id.btnActive)).perform(click());
            //    onView(withId(recycle_view)).check(new RecyclerViewItemCountAssertion(3));

            } else if (i % 3 == 1) {
                onView(withId(id.btnFinished)).perform(click());
            //    onView(withId(recycle_view)).check(new RecyclerViewItemCountAssertion(1));

            } else

                onView(withId(id.btnActivenonpm)).perform(click());
          //  onView(withId(recycle_view)).check(new RecyclerViewItemCountAssertion(3));

        }

    }


}

