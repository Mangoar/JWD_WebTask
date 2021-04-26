package com.example.WebAppPayments.controller.tag;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for custom tag used to display current date
 */
public class MyDateTag extends SimpleTagSupport {

    @Override
    public void doTag() throws IOException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        getJspContext().getOut().print("Today is " + formatter.format(date));
    }
}
