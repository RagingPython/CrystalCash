package ragingpython.crystalcash.entities.wallet;


public class WalletDataContainer {
    public String hash=null;
    public String name=null;
    public double balance;
    public boolean answered=false;

    public WalletDataContainer(String hash) {
        this.hash=hash;
    }
}
