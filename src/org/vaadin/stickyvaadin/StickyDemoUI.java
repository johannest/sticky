package org.vaadin.stickyvaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
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
		VerticalLayout mainLayout = new VerticalLayout();
		setContent(mainLayout);
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		mainLayout.setWidth("100%");
		//mainLayout.setHeight("2200px");
		
		final GridLayout layout = new GridLayout(2,1);
		layout.setSizeFull();
		layout.setMargin(true);
		layout.setSpacing(true);

		Panel buttonPanel = new Panel();
		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setMargin(true);
		buttonLayout.setSpacing(true);
		buttonPanel.setContent(buttonLayout);
		
		mainLayout.addComponent(buttonPanel);
		mainLayout.addComponent(layout);
		
		Button button1 = new Button("Make this button sticky/unsticky");
		button1.setId("test_id_asdsad");
		final Sticky sticky = new Sticky(button1);
		
		Button button2 = new Button("Make this Panel sticky/unsticky");
		buttonPanel.setId("test_id2_asdsad");
		final Sticky sticky2 = new Sticky(buttonPanel);

		button1.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (!sticky.isSticky()) {
					sticky.makeSticky();
				} else {
					sticky.makeUnSticky();
				}
			}
		});
		button2.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (!sticky2.isSticky()) {
					sticky2.makeSticky();
				} else {
					sticky2.makeUnSticky();
				}
			}
		});
		
		final NativeSelect spacingSelect = new NativeSelect("Select top spacing for sticky panel",getSpacingContainer());
		spacingSelect.select("top 0px");
		spacingSelect.setImmediate(true);
		spacingSelect.setNullSelectionAllowed(false);
		spacingSelect.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				Integer pxs = (Integer) spacingSelect.getContainerProperty(spacingSelect.getValue(), "px").getValue();
				sticky2.setTopSpacingInPx(pxs);
			}
		});
		
		
		buttonLayout.addComponents(button1,button2,spacingSelect);
		buttonLayout.setComponentAlignment(button1, Alignment.BOTTOM_LEFT);
		buttonLayout.setComponentAlignment(button2, Alignment.BOTTOM_LEFT);

		for (int i = 0; i < 90; i++) {
			layout.addComponent(new Label(new LoremIpsum().getWords(50)));
		}
	}

	private Container getSpacingContainer() {
		IndexedContainer container = new IndexedContainer();
		container.addContainerProperty("px", Integer.class, 0);
		container.addItem("top 40px").getItemProperty("px").setValue(40);
		container.addItem("top 15px").getItemProperty("px").setValue(15);
		container.addItem("top 10px").getItemProperty("px").setValue(10);
		container.addItem("top 0px").getItemProperty("px").setValue(0);
		return container;
	}

}