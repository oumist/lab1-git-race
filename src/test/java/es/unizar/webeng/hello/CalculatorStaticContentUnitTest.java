package es.unizar.webeng.hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Performs static content unit test to the
 * Calculator JSP template
 *
 * @author Daniel Huici Meseguer (758635)
 * @version 1.0
 * @since 09/17/2020
 *
 */

@RunWith(SpringRunner.class)
@WebMvcTest(CalculatorController.class)
public class CalculatorStaticContentUnitTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    /**
     * Setup Mockito test with web context
     */

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    /**
     * Performs static content test for default
     * values (0)
     */

    @Test
    public void testCalculator() throws Exception {
        this.mockMvc.perform(get("/ultra-calculator"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("values", is("0")))
                .andExpect(model().attribute("message", is("0")));
    }
}
