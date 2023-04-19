package steam.page;

import framework.BasePage;
import steam.page.components.SteamHeader;

public class BaseSteamPage extends BasePage {
    protected SteamHeader steamHeader;

    public BaseSteamPage() {
        super();
        this.steamHeader = new SteamHeader();
    }
}
