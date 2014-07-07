package org.vaadin.stickyvaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.svenjacobs.loremipsum.LoremIpsum;

@SuppressWarnings("serial")
@Theme("stickyvaadin")
public class StickyDemoUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = StickyDemoUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setHeight("2200px");
		setContent(layout);

		Button button = new Button("Make this button sticky");
		button.setId("test_id_asdsad");
		final Sticky sticky = new Sticky(button);

		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (!sticky.isSticky()) {
					sticky.makeSticky();
				} else {
					sticky.makeUnSticky();
				}
			}
		});
		layout.addComponent(button);

		for (int i = 0; i < 20; i++) {
			layout.addComponent(new Label(new LoremIpsum().getWords(40)));
		}
	}

}