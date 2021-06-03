package com.example.application.views.main;

import java.util.Optional;

import com.example.application.views.prestamos.PrestamosView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.main.MainView;
import com.example.application.views.inicio.InicioView;
import com.example.application.views.cuentas.CuentasView;
import com.example.application.views.tarjetas.TarjetasView;
import com.example.application.views.movimientos.MovimientosView;


/**
 * The main view is a top-level placeholder for other views.
 */
@PWA(name = "IngeniaBANK", shortName = "IngeniaBANK", enableInstallPrompt = false)
@Theme(themeFolder = "ingeniabank")
public class MainView extends AppLayout {

    private final Tabs menu;
    private H1 viewTitle;

    public MainView() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        menu = createMenu();
        addToDrawer(createDrawerContent(menu));

    }

    private Component createHeaderContent() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(new DrawerToggle());
        viewTitle = new H1();
        layout.add(viewTitle);
        layout.add(logoutForm());

        return layout;




    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        Image image = new Image ("images/logo1.png", "IngeniaBANK logo");
        logoLayout.add(image);
        image.addClickListener((e -> {
            UI.getCurrent().navigate("Inicio");
        }));
        logoLayout.addClickListener((e -> {
            UI.getCurrent().navigate("Inicio");
        }));

        layout.add(logoLayout, menu);
        layout.getElement().getStyle().set("cursor","pointer");
        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        tabs.add(createMenuItems());
        return tabs;
    }

    private Component[] createMenuItems() {
        return new Tab[]{createTab("🏠   Inicio", InicioView.class), createTab("👥  Cuentas", CuentasView.class),
                createTab("💳  Tarjetas", TarjetasView.class), createTab("🔂  Movimientos", MovimientosView.class),createTab("💱  Prestamos", PrestamosView.class)};
    }

    private static Tab createTab(String text, Class<? extends Component> navigationTarget) {
        final Tab tab = new Tab();
        tab.add(new RouterLink(text, navigationTarget));
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);
        viewTitle.setText(getCurrentPageTitle());
    }

    private Optional<Tab> getTabForComponent(Component component) {
        return menu.getChildren().filter(tab -> ComponentUtil.getData(tab, Class.class).equals(component.getClass()))
                .findFirst().map(Tab.class::cast);
    }

    private Component logoutForm() {
        Avatar loggedUser = new Avatar();
        loggedUser.setName("Admin");
        loggedUser.setImage("images/user.png");
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.setTarget(loggedUser);
        contextMenu.setOpenOnClick(true);
        contextMenu.addItem("Log out", e -> {
            contextMenu.getUI().ifPresent(ui -> ui.getPage().setLocation("/logout"));
        });
        return loggedUser;
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
