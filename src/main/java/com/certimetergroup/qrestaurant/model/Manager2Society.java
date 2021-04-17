package com.certimetergroup.qrestaurant.model;

public class Manager2Society {
    private Integer idManager2Society;
    private Integer idSocietyFK;
    private Integer idManagerFK;

    public Manager2Society(Integer idManager2Society, Integer idSocietyFK, Integer idManagerFK) {
        this.idManager2Society = idManager2Society;
        this.idSocietyFK = idSocietyFK;
        this.idManagerFK = idManagerFK;
    }

    public Integer getIdManager2Society() {
        return idManager2Society;
    }

    public void setIdManager2Society(Integer idManager2Society) {
        this.idManager2Society = idManager2Society;
    }

    public Integer getIdSocietyFK() {
        return idSocietyFK;
    }

    public void setIdSocietyFK(Integer idSocietyFK) {
        this.idSocietyFK = idSocietyFK;
    }

    public Integer getIdManagerFK() {
        return idManagerFK;
    }

    public void setIdManagerFK(Integer idManagerFK) {
        this.idManagerFK = idManagerFK;
    }
}
