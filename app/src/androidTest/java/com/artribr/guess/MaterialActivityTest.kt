package com.artribr.guess

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MaterialActivityTest {
    //取得要進行測試對象的activity
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MaterialActivity>(MaterialActivity::class.java)

    @Test
    fun guessWrong() {
        //用來取得resources，例如string
        val resources = activityTestRule.activity.resources
        //從測試對象的activity中取得需要的值
        val secret = activityTestRule.activity.secretNumber.secret
//        val n = 5
//        Espresso.onView(ViewMatchers.withId(R.id.ed_number)).perform(ViewActions.typeText("5"))
//        Espresso.onView(ViewMatchers.withId(R.id.btn_ok)).perform(ViewActions.click())
        for(n in 1..10){
            if (n != secret) {
                //取得文字輸入框，然後清空
                onView(withId(R.id.ed_number)).perform(clearText())
                //取得文字輸入框，輸入n的數字
                onView(withId(R.id.ed_number)).perform(typeText(n.toString()))
                //按下ok按鈕，進行比對
                onView(withId(R.id.btn_ok)).perform(click())

                //驗證比較結果的message
                val message =
                    if (n < secret) resources.getString(R.string.bigger)
                    else resources.getString(R.string.smaller)

                //從畫面上取得message，確認是否有往畫面取得跟message的文字
                onView(withText(message)).check(matches(isDisplayed()))

                //最後按下ok按鈕, 關閉AlertDialog
                onView(withText(resources.getString(R.string.ok))).perform(click())
            }
        }

    }
}