package com.example.coco.demoapp.X_AllTests

import android.test.suitebuilder.TestSuiteBuilder
import com.example.coco.demoapp.A_LoginScreenTest.LoginTest
import com.example.coco.demoapp.B_ListScreenTest.ListScreenTest
import junit.framework.TestSuite
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.Suite
import android.test.suitebuilder.*;



/**
 * Created by alexandrumihai1 on 23/11/2017.
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(LoginTest::class, ListScreenTest::class)
class AllTests

