package com.example.kolin.movieapplication;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.kolin.movieapplication.presentation.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by kolin on 09.08.2016.
 */

public class MainActivityTest{

    @Rule
    public ActivityTestRule<MainActivity> menuActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Test
    public void testOpenDetailedActivityAndBack(){
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

        onView(withId(R.id.image_image)).check(matches(isDisplayed()));

        pressBack();

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    public void testOnSwipeViewPager(){
        onView(withId(R.id.viewP)).perform(swipeLeft());

        onView(withId(R.id.rvFavorite)).check(matches(isDisplayed()));

        onView(withId(R.id.viewP)).perform(swipeRight());

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    public void testOnClickMenu(){
        onView(withHint(R.menu.menu_navigation)).perform(click());

        onView(withId(R.id.date_item)).check(matches(isDisplayed()));
    }


}
