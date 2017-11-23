package com.example.coco.demoapp.A_LoginScreenTest;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.InputType;


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
import static android.support.test.espresso.intent.Intents.init;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.release;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;


/**
 * Created by alexandrumihai1 on 23/11/2017.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
    @RunWith(AndroidJUnit4.class)
    public class LoginTest {


        @Rule
        public ActivityTestRule<MainActivity> mActivityRule =
                new ActivityTestRule<>(MainActivity.class);

//
//        //First of all, check if everything is present
        @Test
    public void test_1checkIfAllElementsAreDisplayed(){
            onView(withId(R.id.txtPassword)).check(matches(isDisplayed()));
            onView(withId(R.id.txtUserName)).check(matches(isDisplayed()));
            onView(withId(R.id.btnLogin)).check(matches(isDisplayed()));
            onView(withId(R.id.cboxPick)).check(matches(isDisplayed()));
            onView(withId(R.id.cboxRemember)).check(matches(isDisplayed()));
            onView(withId(R.id.txtAlert)).check(matches(not(isDisplayed())));
        }

        @Test
        public void test_2checkHints(){
            onView(withId(R.id.txtPassword)).check(matches(withHint("Password")));
            onView(withId(R.id.txtUserName)).check(matches(withHint("Username")));

        }

        @Test
    public void test_3clickableItems(){
            onView(withId(R.id.cboxRemember)).check(matches(isClickable()));
            onView(withId(R.id.cboxPick)).check(matches(isClickable()));
            onView(withId(R.id.btnLogin)).check(matches(isClickable()));
            onView(withId(R.id.txtPassword)).check(matches(isClickable()));
            onView(withId(R.id.txtUserName)).check(matches(isClickable()));
        }

        @Test
    public void test_4wrongCredentialsTest(){

        onView(withId(R.id.txtUserName)).perform(replaceText("gigel"));
        onView(withId(R.id.txtPassword)).perform(replaceText("sdfsd"));
        onView(withId(R.id.btnLogin)).perform(click());
        onView(withId(R.id.txtAlert)).check(matches(isDisplayed()));
        onView(withId(R.id.txtAlert)).check(matches(withText("Invalid username and/or password.")));

        onView(withId(R.id.txtPassword)).perform(clearText());
        onView(withId(R.id.txtUserName)).perform(clearText());

            onView(withId(R.id.txtUserName)).perform(replaceText("gggg"));
            onView(withId(R.id.txtPassword)).perform(replaceText("gighhhel"));
            onView(withId(R.id.btnLogin)).perform(click());
            onView(withId(R.id.txtAlert)).check(matches(isDisplayed()));
            onView(withId(R.id.txtAlert)).check(matches(withText("Invalid username and/or password.")));

            onView(withId(R.id.txtPassword)).perform(clearText());
            onView(withId(R.id.txtUserName)).perform(clearText());

            onView(withId(R.id.txtUserName)).perform(replaceText("acc_gigel"));
            onView(withId(R.id.txtPassword)).perform(replaceText("gshel"));
            onView(withId(R.id.btnLogin)).perform(click());
            onView(withId(R.id.txtAlert)).check(matches(isDisplayed()));
            onView(withId(R.id.txtAlert)).check(matches(withText("Invalid username and/or password.")));

            onView(withId(R.id.txtPassword)).perform(clearText());
            onView(withId(R.id.txtUserName)).perform(clearText());

            onView(withId(R.id.txtUserName)).perform(replaceText("gigel"));
            onView(withId(R.id.txtPassword)).perform(replaceText("parolagigel1"));
            onView(withId(R.id.btnLogin)).perform(click());
            onView(withId(R.id.txtAlert)).check(matches(isDisplayed()));
            onView(withId(R.id.txtAlert)).check(matches(withText("Invalid username and/or password.")));

            onView(withId(R.id.txtPassword)).perform(clearText());
            onView(withId(R.id.txtUserName)).perform(clearText());

            onView(withId(R.id.txtUserName)).perform(replaceText("gigel"));
            onView(withId(R.id.txtPassword)).perform(replaceText("gigel"));
            onView(withId(R.id.btnLogin)).perform(click());
            onView(withId(R.id.txtAlert)).check(matches(isDisplayed()));
            onView(withId(R.id.txtAlert)).check(matches(withText("Invalid username and/or password.")));

            onView(withId(R.id.txtPassword)).perform(clearText());
            onView(withId(R.id.txtUserName)).perform(clearText());

            onView(withId(R.id.txtUserName)).perform(replaceText("acc_gigel"));
            onView(withId(R.id.txtPassword)).perform(replaceText("parolagigel2"));
            onView(withId(R.id.btnLogin)).perform(click());
            onView(withId(R.id.txtAlert)).check(matches(isDisplayed()));
            onView(withId(R.id.txtAlert)).check(matches(withText("Invalid username and/or password.")));


        }

            @Test
        public void test_5checkPeekPasswordFunctionality(){
            onView(withId(R.id.txtPassword)).perform(clearText());
            onView(withId(R.id.txtUserName)).perform(clearText());
            onView(withId(R.id.txtUserName)).perform(replaceText("acc_gigel"));
            onView(withId(R.id.txtPassword)).perform(replaceText("parolagigel2"));

           // onView(withId(R.id.cboxPick)).check(matches(isChecked())).perform(scrollTo(),click());
        for(int i=0;i<20;i++){


        if(i%2!=0) {
            onView(withId(R.id.cboxPick)).perform(click());
            onView(withId(R.id.txtPassword)).check(matches(withInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)));
        }
        else{
            onView(withId(R.id.cboxPick)).perform(click());
            onView(withId(R.id.txtPassword)).check(matches(withInputType(InputType.TYPE_CLASS_TEXT)));
        }

        }

        }

        @Test
    public void test_6testNoInput(){
            onView(withId(R.id.txtPassword)).perform(clearText());
            onView(withId(R.id.txtUserName)).perform(clearText());

            for(int i=0;i<15;i++){
                onView(withId(R.id.btnLogin)).perform(click());
                onView(withId(R.id.txtAlert)).check(matches(withText("There are empty fields! ")));
            }


        }


    @Test
    public void test_7loginOk(){

        onView(withId(R.id.txtPassword)).perform(clearText());
        onView(withId(R.id.txtUserName)).perform(clearText());
        onView(withId(R.id.txtUserName)).perform(replaceText("acc_gigel"));
        onView(withId(R.id.txtPassword)).perform(replaceText("parolagigel1"));

        init();
        onView(withId(R.id.btnLogin)).perform(click());
        intended(hasComponent(ListScreen.class.getName()));
        release();

    }


    }
