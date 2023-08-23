package provider;

public class CashProvider {
    private long card;
    private long hashCode;
    public boolean isAuthorized;

    public CashProvider(long card, long hashCode, boolean isAuthorized) {
        this.card = card;
        this.hashCode = hashCode;
        this.isAuthorized = isAuthorized;
    }

    public long getCard() {
        return card;
    }

    public long getHashCode() {
        return hashCode;
    }

    public void setHashCode(long hashCode) {
        this.hashCode = hashCode;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }
}
