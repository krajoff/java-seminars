import java.util.*;

public class Main {
    public static void main(String[] args) {
        Weight weight1 = new Weight(12L, 1L, 1, 0d, 0d, -1L);
        Weight weight2 = new Weight(22L, 2L, 1, 100d, 0d, 1L);
        Weight weight3 = new Weight(112L, 3L, 1, 100d, 90d, 11L);
        Weight weight5 = new Weight(222L, 5L, 1, 100d, 180d, 3L);

        List<Weight> weightList = new ArrayList<>();
        weightList.add(weight1);
        weightList.add(weight2);
        weightList.add(weight3);
        weightList.add(weight5);

        Optional<Weight> tempWeight;
        Weight weight = weight5;
        long reference = weight.getReference();
        Complex complexTotalWeight = weight.getComplexWeight();
        while (reference != -1) {
            long finalReference = reference;
            tempWeight = weightList
                    .stream()
                    .filter(w -> w.getInsideId()
                            == finalReference).findFirst();
            if (tempWeight.isPresent()) {
                System.out.print(complexTotalWeight + " + ");
                complexTotalWeight = complexTotalWeight.plus(tempWeight.get().getComplexWeight());
                System.out.print(tempWeight.get().getComplexWeight() + " = ");
                System.out.println(complexTotalWeight);
                reference = tempWeight.get().getReference();
            } else {
                reference = -1;
            }
        }
        weight.setComplexTotalWeight(complexTotalWeight);
        weight.setMagTotalWeight(complexTotalWeight.abs());
        weight.setPhaseTotalWeight(complexTotalWeight.phase() * 180 / 3.1415);


        System.out.println(hasCycles(weightList));
        System.out.println(weight);
    }


    public static boolean hasCycles(List<Weight> weightList) {
        Set<Long> visited = new HashSet<>();
        for (Weight weight : weightList) {
            if (isCyclic(weight, visited, weightList)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isCyclic(Weight weight, Set<Long> visited, List<Weight> weightList) {
        while (weight.getReference() != -1) {
            if (visited.contains(weight.getReference())) {
                return true;
            }
            visited.add(weight.getReference());
            Weight finalWeight = weight;
            Optional<Weight> nextWeightOpt = weightList
                    .stream()
                    .filter(w -> Objects
                            .equals(finalWeight
                                    .getInsideId(), finalWeight.getReference()))
                    .findFirst();
            if (!nextWeightOpt.isPresent()) {
                finalWeight.setReference(-1L);
                return false;
            }
            weight = nextWeightOpt.get();
        }
        return false;
    }


}