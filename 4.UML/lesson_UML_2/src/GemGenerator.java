public class GemGenerator extends ItemFabric{
    @Override
    public iGameItem createItem() {
        System.out.println("Создан новый сундук для драг.камней");
        return new GemReward();
    };
}
