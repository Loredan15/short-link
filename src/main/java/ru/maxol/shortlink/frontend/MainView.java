package ru.maxol.shortlink.frontend;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    public MainView() {
        H1 header = new H1("Test H1");
        add(header);
        add(new Text("Welcome to MainView."));
    }

}
