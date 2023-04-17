package steam.page;

import framework.BasePage;

public class BaseSteamPage extends BasePage {
    protected SteamHeader steamHeader;

    public BaseSteamPage() {
        super();
        this.steamHeader = new SteamHeader();
    }
}
