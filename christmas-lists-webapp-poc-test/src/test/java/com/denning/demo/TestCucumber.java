package com.denning.demo;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Unit test for simple App.
 */
@RunWith(Cucumber.class)
@CucumberOptions(glue = "com.denning.demo.acceptance")
public class TestCucumber
{
}
