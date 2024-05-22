import java.util.HashSet;
import java.util.Set;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Weight {

    private Long id;

    private Long insideId;

    private Integer plane;

    private Double magWeight;

    private Double phaseWeight;

    private Long reference;

    private Double magTotalWeight;

    private Double phaseTotalWeight;

    private Complex complexTotalWeight;

    public Complex getComplexWeight() {
        return new Complex(this.magWeight *
                cos(Math.toRadians(this.phaseWeight)),
                this.magWeight * sin(Math.toRadians(this.phaseWeight)));
    }

    public Weight(Long id, Long insideId, Integer plane, Double magWeight, Double phaseWeight, Long reference) {
        this.id = id;
        this.insideId = insideId;
        this.plane = plane;
        this.magWeight = magWeight;
        this.phaseWeight = phaseWeight;
        this.reference = reference;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInsideId() {
        return insideId;
    }

    public void setInsideId(Long insideId) {
        this.insideId = insideId;
    }

    public Integer getPlane() {
        return plane;
    }

    public void setPlane(Integer plane) {
        this.plane = plane;
    }

    public Double getMagWeight() {
        return magWeight;
    }

    public void setMagWeight(Double magWeight) {
        this.magWeight = magWeight;
    }

    public Double getPhaseWeight() {
        return phaseWeight;
    }

    public void setPhaseWeight(Double phaseWeight) {
        this.phaseWeight = phaseWeight;
    }

    public Long getReference() {
        return reference;
    }

    public void setReference(Long reference) {
        this.reference = reference;
    }

    public Double getMagTotalWeight() {
        return magTotalWeight;
    }

    public void setMagTotalWeight(Double magTotalWeight) {
        this.magTotalWeight = magTotalWeight;
    }

    public Double getPhaseTotalWeight() {
        return phaseTotalWeight;
    }

    public void setPhaseTotalWeight(Double phaseTotalWeight) {
        this.phaseTotalWeight = phaseTotalWeight;
    }

    public Complex getComplexTotalWeight() {
        return complexTotalWeight;
    }

    public void setComplexTotalWeight(Complex complexTotalWeight) {
        this.complexTotalWeight = complexTotalWeight;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "id=" + id +
                ", insideId=" + insideId +
                ", plane=" + plane +
                ", magWeight=" + magWeight +
                ", phaseWeight=" + phaseWeight +
                ", reference=" + reference +
                ", magTotalWeight=" + magTotalWeight +
                ", phaseTotalWeight=" + phaseTotalWeight +
                ", complexTotalWeight=" + complexTotalWeight +
                '}';
    }



}