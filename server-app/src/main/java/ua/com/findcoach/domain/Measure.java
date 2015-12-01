package ua.com.findcoach.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Measure {
    @Id
    @Column(name = "measure_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "measure_id_seq_gen")
    @SequenceGenerator(name = "measure_id_seq_gen", sequenceName = "measure_measure_id_seq")
    private Integer measureId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "padawan_id")
    private Padawan padawan;

    @Column
    private LocalDate measureDate;

    @Column(columnDefinition = "numeric(4)")
    private Short height;

    @Column
    private BigDecimal weight;

    @Column(columnDefinition = "numeric(2)")
    private Short fatPercentage;

    @Column(columnDefinition = "numeric(4)")
    private Short neck;

    @Column(columnDefinition = "numeric(4)")
    private Short rightUpperArm;

    @Column(columnDefinition = "numeric(4)")
    private Short leftUpperArm;

    @Column(columnDefinition = "numeric(4)")
    private Short rightForearm;

    @Column(columnDefinition = "numeric(4)")
    private Short leftForearm;

    @Column(columnDefinition = "numeric(4)")
    private Short chest;

    @Column(columnDefinition = "numeric(4)")
    private Short bust;

    @Column(columnDefinition = "numeric(4)")
    private Short midriff;

    @Column(columnDefinition = "numeric(4)")
    private Short waist;

    @Column(columnDefinition = "numeric(4)")
    private Short abdomen;

    @Column(columnDefinition = "numeric(4)")
    private Short hips;

    @Column(columnDefinition = "numeric(4)")
    private Short buttocks;

    @Column(columnDefinition = "numeric(4)")
    private Short thigh;

    @Column(columnDefinition = "numeric(4)")
    private Short knee;

    @Column(columnDefinition = "numeric(4)")
    private Short calf;

    @Column(columnDefinition = "numeric(4)")
    private Short ankle;

    public Integer getMeasureId() {
        return measureId;
    }

    public void setMeasureId(Integer measureId) {
        this.measureId = measureId;
    }

    public Padawan getPadawan() {
        return padawan;
    }

    public void setPadawan(Padawan padawan) {
        this.padawan = padawan;
    }

    public LocalDate getMeasureDate() {
        return measureDate;
    }

    public void setMeasureDate(LocalDate measureDate) {
        this.measureDate = measureDate;
    }

    public Short getHeight() {
        return height;
    }

    public void setHeight(Short height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Short getNeck() {
        return neck;
    }

    public void setNeck(Short neck) {
        this.neck = neck;
    }

    public Short getRightUpperArm() {
        return rightUpperArm;
    }

    public void setRightUpperArm(Short rightUpperArm) {
        this.rightUpperArm = rightUpperArm;
    }

    public Short getLeftUpperArm() {
        return leftUpperArm;
    }

    public void setLeftUpperArm(Short leftUpperArm) {
        this.leftUpperArm = leftUpperArm;
    }

    public Short getRightForearm() {
        return rightForearm;
    }

    public void setRightForearm(Short rightForearm) {
        this.rightForearm = rightForearm;
    }

    public Short getLeftForearm() {
        return leftForearm;
    }

    public void setLeftForearm(Short leftForearm) {
        this.leftForearm = leftForearm;
    }

    public Short getChest() {
        return chest;
    }

    public void setChest(Short chest) {
        this.chest = chest;
    }

    public Short getBust() {
        return bust;
    }

    public void setBust(Short bust) {
        this.bust = bust;
    }

    public Short getMidriff() {
        return midriff;
    }

    public void setMidriff(Short midriff) {
        this.midriff = midriff;
    }

    public Short getWaist() {
        return waist;
    }

    public void setWaist(Short waist) {
        this.waist = waist;
    }

    public Short getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(Short abdomen) {
        this.abdomen = abdomen;
    }

    public Short getHips() {
        return hips;
    }

    public void setHips(Short hips) {
        this.hips = hips;
    }

    public Short getButtocks() {
        return buttocks;
    }

    public void setButtocks(Short buttocks) {
        this.buttocks = buttocks;
    }

    public Short getThigh() {
        return thigh;
    }

    public void setThigh(Short thigh) {
        this.thigh = thigh;
    }

    public Short getKnee() {
        return knee;
    }

    public void setKnee(Short knee) {
        this.knee = knee;
    }

    public Short getCalf() {
        return calf;
    }

    public void setCalf(Short calf) {
        this.calf = calf;
    }

    public Short getAnkle() {
        return ankle;
    }

    public void setAnkle(Short ankle) {
        this.ankle = ankle;
    }

    public Short getFatPercentage() {
        return fatPercentage;
    }

    public void setFatPercentage(Short fatPercentage) {
        this.fatPercentage = fatPercentage;
    }
}
