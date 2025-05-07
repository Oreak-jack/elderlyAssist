package cn.edu.tute.schoolproject.entity;

public class Relationship {
        private Integer id;
        private Integer FamilyId;
        private Integer OldId;

    @Override
    public String toString() {
        return "Relationship{" +
                "id=" + id +
                ", FamilyId=" + FamilyId +
                ", OldId=" + OldId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFamilyId() {
        return FamilyId;
    }

    public void setFamilyId(Integer familyId) {
        FamilyId = familyId;
    }

    public Integer getOldId() {
        return OldId;
    }

    public void setOldId(Integer oldId) {
        OldId = oldId;
    }

    public Relationship(Integer id, Integer familyId, Integer oldId) {
        this.id = id;
        FamilyId = familyId;
        OldId = oldId;
    }

    public Relationship() {
    }
}
